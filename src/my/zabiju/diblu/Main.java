package my.zabiju.diblu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Main {

    private List<ClenRodiny> davaji;
    private List<ClenRodiny> dostavaji;
    private List<List<String>> all;
    private EmailSender sender;


    public Main() {
        all = new ArrayList<>();
        List<String> tempResult = null;
        sender = new EmailSender();
        boolean done = false;
        /*while (!done) {
            done = rozdejAPosli();
        }*/
        for (int i = 0; i < 10; i++) {
            while (tempResult == null) {
                tempResult = rozdej();
            }
            print(tempResult, i);
            tempResult = null;
        }
        //printToConsole(tempResult,0);
        System.out.println("hotovo");
        /*for (int i = 0; i < 100; i++) {
            while (tempResult == null) {
                tempResult = rozdej();
           }

           if (!checkAllIfDifferent(tempResult)) {
               System.err.println();
           } else {
               print(tempResult, i);
                all.add(tempResult);
                tempResult = null;
            }
        }*/

    }

    private void printToConsole(List<String> values, int number) {
        System.out.println("Možnost: " + (number + 1));
        for (String item : values) {
            System.out.println(item);
        }
        System.out.println();
    }


    private void print(List<String> values, int number) {
        try (FileWriter fw = new FileWriter("možnosti.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Možnost: " + (number + 1));
            for (String item : values) {
                out.println(item);
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean checkAllIfDifferent(List<String> tempResult) {
        for (List<String> item : all) {
            if (item.equals(tempResult)) {
                return false;
            }
        }
        return true;
    }


    private List<ClenRodiny> initLidi() {
        List<ClenRodiny> lide = new ArrayList<>();
        // sport
        lide.add(createClen(Person.Dana, Arrays.asList(Person.Kamila), Family.SPORT));
        lide.add(createClen(Person.Boba, Arrays.asList(Person.Honza), Family.SPORT));
        lide.add(createClen(Person.Barca, Arrays.asList(Person.Kuba), Family.SPORT));
        lide.add(createClen(Person.Tony, Arrays.asList(Person.Lada), Family.SPORT));
        lide.add(createClen(Person.Janicka, Arrays.asList(Person.Stepa), Family.SPORT));
        lide.add(createClen(Person.Mata, Collections.emptyList(), Family.SPORT));

        // brno
        lide.add(createClen(Person.Hana, Arrays.asList(Person.Janicka), Family.BRNO));
        lide.add(createClen(Person.Lada, Arrays.asList(Person.Petka), Family.BRNO));

        // mimi
        lide.add(createClen(Person.Petka, Arrays.asList(Person.Tony), Family.MIMINO, Family.MAIN));
        lide.add(createClen(Person.Honza, Arrays.asList(Person.Dana), Family.MIMINO));
        lide.add(createClen(Person.Pata, Arrays.asList(Person.Martina), Family.MIMINO));

        // main
        lide.add(createClen(Person.Stepa, Arrays.asList(Person.Petr_Last), Family.MAIN));
        lide.add(createClen(Person.Petr_Dibl, Arrays.asList(Person.Barca), Family.MAIN));

        // my
        lide.add(createClen(Person.Martina, Arrays.asList(Person.Boba), Family.PRAHA, Family.MAIN));
        lide.add(createClen(Person.Petr_Last, Arrays.asList(Person.Petr_Dibl), Family.PRAHA));

        // caj
        lide.add(createClen(Person.Kuba, Arrays.asList(Person.Hana), Family.CAJ, Family.MAIN));
        lide.add(createClen(Person.Kamila, Arrays.asList(Person.Pata), Family.CAJ));

        return lide;

    }


    public List<String> rozdej() throws NullPointerException {
        davaji = initLidi();
        dostavaji = initLidi();
        List<String> result = new ArrayList<>();
        String REGEX = "%-3d %-12s dava: %-32s";
        for (int i = 0; i < davaji.size(); i++) {

            ClenRodiny dava = davaji.get(i);
            ClenRodiny dostava = najdi(dava);
            try {
                dostava.setDostal(true);
                result.add(String.format(REGEX, i + 1, dava.name(), dostava.name()));
            } catch (NullPointerException e) {
                return null;
            }
        }
        return result;
    }


    public boolean rozdejAPosli() {
        davaji = initLidi();
        dostavaji = initLidi();
        for (int i = 0; i < davaji.size(); i++) {

            ClenRodiny dava = davaji.get(i);
            ClenRodiny dostava = najdi(dava);
            try {
                dostava.setDostal(true);
                dava.setObdarovany(dostava);
            } catch (NullPointerException e) {
                return false;
            }
        }
        //posli(davaji);
        return true;

    }


    public void posli(List<ClenRodiny> davaji) {
        for (ClenRodiny item : davaji) {
            sender.sendEmail(item);
        }
        System.out.println("poslano");
    }


    public ClenRodiny najdi(ClenRodiny dava) {
        Random rand = new Random();
        ClenRodiny clen;
        for (int i = 0; i < dostavaji.size(); i++) {
            int randomIndex = rand.nextInt(davaji.size());
            clen = dostavaji.get(randomIndex);
            if (!clen.dostal() && !contains(dava.getZakazaneRodiny(), clen.getZakazaneRodiny()) && !dava.davalMu(clen.name())) {
                return clen;
            }
        }
        return null;
    }


    private boolean contains(List<Family> zakaz, List<Family> dostavajici) {
        for (Family item : zakaz) {
            if (dostavajici.contains(item)) {
                return true;
            }
        }
        return false;
    }


    private ClenRodiny createClen(Person name, List<Person> daval, Family... families) {
        ClenRodiny clen = new ClenRodiny(name, daval, families);
        return clen;
    }


    public static void main(String[] args) {
        new Main();
    }
}
