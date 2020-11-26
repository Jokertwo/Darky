package my.jokertwo.chrismas;

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

    private List<FamilyPerson> davaji;
    private List<FamilyPerson> dostavaji;
    private List<List<String>> all;


    public Main() {
        all = new ArrayList<>();
        List<String> tempResult = null;

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


    private List<FamilyPerson> initLidi() {
        List<FamilyPerson> lide = new ArrayList<>();
        // sport
        lide.add(createClen(People.Dana, Arrays.asList(People.Kamila, People.Petka), Family.SPORT));
        lide.add(createClen(People.Boba, Arrays.asList(People.Honza, People.Lada), Family.SPORT));
        lide.add(createClen(People.Barca, Arrays.asList(People.Kuba, People.Honza), Family.SPORT));
        lide.add(createClen(People.Tony, Arrays.asList(People.Lada, People.Kamila), Family.SPORT));
        //lide.add(createClen(People.Janicka, Arrays.asList(People.Stepa, People.Kuba), Family.SPORT));
        lide.add(createClen(People.Mata, Arrays.asList(People.Martina), Family.SPORT));
        lide.add(createClen(People.Misa, Collections.emptyList(), Family.SPORT));

        // brno
        lide.add(createClen(People.Hana, Arrays.asList(/*People.Janicka,*/ People.Stepa), Family.BRNO));
        lide.add(createClen(People.Lada, Arrays.asList(People.Petka, People.Tony), Family.BRNO));

        // volleyball
        lide.add(createClen(People.Petka, Arrays.asList(People.Tony, People.Hana), Family.VOLLEYBALL, Family.MAIN));
        lide.add(createClen(People.Honza, Arrays.asList(People.Dana, People.Barca), Family.VOLLEYBALL));
        lide.add(createClen(People.Patrik, Arrays.asList(People.Martina, People.Petr_Dibl), Family.VOLLEYBALL));
        lide.add(createClen(People.Filip, Collections.emptyList(), Family.VOLLEYBALL));

        // main
        lide.add(createClen(People.Stepa, Arrays.asList(People.Petr_Last, People.Boba), Family.MAIN));
        lide.add(createClen(People.Petr_Dibl, Arrays.asList(People.Barca, People.Patrik), Family.MAIN));

        // prague
        lide.add(createClen(People.Martina, Arrays.asList(People.Boba, People.Mata), Family.PRAGUE, Family.MAIN));
        lide.add(createClen(People.Petr_Last, Arrays.asList(People.Petr_Dibl/*, People.Janicka*/), Family.PRAGUE));

        // tea
        lide.add(createClen(People.Kuba, Arrays.asList(People.Hana, People.Petr_Last), Family.TEA, Family.MAIN));
        lide.add(createClen(People.Kamila, Arrays.asList(People.Patrik, People.Dana), Family.TEA));
        lide.add(createClen(People.Matous, Collections.emptyList(), Family.TEA));

        return lide;

    }


    public List<String> rozdej() throws NullPointerException {
        davaji = initLidi();
        dostavaji = initLidi();
        List<String> result = new ArrayList<>();
        String REGEX = "%-3d %-12s dava: %-32s";
        for (int i = 0; i < davaji.size(); i++) {

            FamilyPerson dava = davaji.get(i);
            FamilyPerson dostava = najdi(dava);
            try {
                dostava.setGotten(true);
                result.add(String.format(REGEX, i + 1, dava.name(), dostava.name()));
            } catch (NullPointerException e) {
                return null;
            }
        }
        return result;
    }


    public FamilyPerson najdi(FamilyPerson dava) {
        Random rand = new Random();
        FamilyPerson clen;
        for (int i = 0; i < dostavaji.size(); i++) {
            int randomIndex = rand.nextInt(davaji.size());
            clen = dostavaji.get(randomIndex);
            if (!clen.alreadyGotten() && !contains(dava.getForbiddenFamily(), clen.getForbiddenFamily()) && !dava.davalMu(clen.name())) {
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


    private FamilyPerson createClen(People name, List<People> daval, Family... families) {
        FamilyPerson clen = new FamilyPerson(name, daval, families);
        return clen;
    }


    public static void main(String[] args) {
        new Main();
    }
}
