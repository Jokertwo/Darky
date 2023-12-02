package my.jokertwo.chrismas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    private List<FamilyPerson> davaji;
    private List<FamilyPerson> dostavaji;
    private List<FamilyPerson> all;


    public Main(boolean rozdej) {
        all = initLidi();
        if (rozdej) {
            List<String> tempResult = null;

            for (int i = 0; i < 10; i++) {
                while (tempResult == null) {
                    tempResult = rozdej();
                }
                print(tempResult, i);
                tempResult = null;
            }
        } else {
            Path path = Paths.get("src/main/resources/možnosti.txt");
            try (Stream<String> lines = Files.lines(path)) {
                lines.forEach(s -> {
                    if (!s.trim().isEmpty()) {
                        char c = s.toCharArray()[0];
                        if (Character.isDigit(c)) {
                            String[] strings = s.split("\\s");
                            List<String> stringList = Arrays.stream(strings).filter(s1 -> !s1.isEmpty())
                                    .collect(Collectors.toList());
                            People men = People.valueOf(stringList.get(3));
                            all.stream().filter(person -> person.getName().equals(men)).findFirst()
                                    .ifPresent(familyPerson -> {
                                        System.out.println(stringList.get(1));
                                        System.out.println(
                                                "Ahoj tajný Ježíšku, letos dáváš dáreček " + familyPerson.getNick());
                                        System.out.println();
                                    });

                        }
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("hotovo");
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


    private List<FamilyPerson> initLidi() {
        List<FamilyPerson> lide = new ArrayList<>();
        // sport
        lide.add(createClen(People.Dana, Arrays.asList(People.Kamila, People.Petka, People.Martina, People.Petr_Last),
                "Dane", Family.SPORT));
        lide.add(createClen(People.Boba, Arrays.asList(People.Honza, People.Lada, People.Filip, People.Martina),
                "Bobovi", Family.SPORT));
        lide.add(createClen(People.Barca, Arrays.asList(People.Kuba, People.Honza, People.Petr_Last, People.Petr_Dibl),
                "Barce", Family.SPORT));
        lide.add(
                createClen(People.Tony, Arrays.asList(People.Lada, People.Kamila, People.Kuba, People.Matous), "Tonymu",
                        Family.SPORT));
        // lide.add(createClen(People.Janicka, Arrays.asList(People.Stepa, People.Kuba), Family.SPORT));
        lide.add(createClen(People.Mata, Arrays.asList(People.Martina, People.Stepa, People.Kuba), "Matějovi",
                Family.SPORT));
        //lide.add(createClen(People.Misa, Arrays.asList(People.Matous, People.Hana),"Míše", Family.SPORT));
        lide.add(createClen(People.Alzbeta, Arrays.asList(), "Bětušce", Family.SPORT));

        // brno
        //lide.add(createClen(People.Hana, Arrays.asList(/*People.Janicka,*/ People.Stepa, People.Tony, People.Dana),"Haně", Family.BRNO));
        //lide.add(createClen(People.Lada, Arrays.asList(People.Petka, People.Tony, People.Petr_Dibl, People.Patrik),"Láďovi", Family.BRNO));

        // volleyball
        lide.add(createClen(People.Petka, Arrays.asList(People.Tony, People.Hana, People.Misa, People.Kamila), "Peťce",
                Family.VOLLEYBALL,
                Family.MAIN));
        lide.add(createClen(People.Honza, Arrays.asList(People.Dana, People.Barca, People.Kamila, People.Misa),
                "Honzovi", Family.VOLLEYBALL));
        lide.add(
                createClen(People.Patrik, Arrays.asList(People.Martina, People.Petr_Dibl, People.Lada, People.Stepa),
                        "Patrikovi", Family.VOLLEYBALL));
        lide.add(createClen(People.Filip, Arrays.asList(People.Mata, People.Tony), "Filipovi", Family.VOLLEYBALL));

        // main
        lide.add(createClen(People.Stepa, Arrays.asList(People.Petr_Last, People.Boba, People.Honza, People.Filip),
                "Šťepě", Family.MAIN));
        lide.add(createClen(People.Petr_Dibl, Arrays.asList(People.Barca, People.Patrik, People.Boba, People.Lada),
                "Petru Díblíkovi", Family.MAIN));

        // prague
        lide.add(createClen(People.Martina, Arrays.asList(People.Boba, People.Mata, People.Hana, People.Honza),
                "Martině", Family.PRAGUE,
                Family.MAIN));
        lide.add(createClen(People.Petr_Last,
                Arrays.asList(People.Petr_Dibl/*, People.Janicka*/, People.Barca, People.Petka),
                "Petru Laštovkovi",
                Family.PRAGUE));
        lide.add(createClen(People.David, Arrays.asList(), "Davídkovi", Family.PRAGUE));
        lide.add(createClen(People.Stepan, Arrays.asList(), "Štepánkovi", Family.PRAGUE));

        // tea
        lide.add(createClen(People.Kuba, Arrays.asList(People.Hana, People.Petr_Last, People.Patrik, People.Boba),
                "Kubovi", Family.TEA,
                Family.MAIN));
        lide.add(
                createClen(People.Kamila, Arrays.asList(People.Patrik, People.Dana, People.Petka, People.Mata), "Kamče",
                        Family.TEA));
        lide.add(createClen(People.Matous, Arrays.asList(People.Dana, People.Barca), "Matoušovi", Family.TEA));
        lide.add(createClen(People.Vojta, Arrays.asList(), "Vojtovi", Family.TEA));

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
                dostava.setGotIt(true);
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
            if (!clen.isGotIt() && !contains(dava.getForbiddenFamily(), clen.getForbiddenFamily())
                    && !dava.davalMu(clen.name())) {
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


    private FamilyPerson createClen(People name, List<People> daval, String nick, Family... families) {
        return new FamilyPerson(name, daval, nick, families);
    }


    public static void main(String[] args) {
        new Main(false);
    }
}
