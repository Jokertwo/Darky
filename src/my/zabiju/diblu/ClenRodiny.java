package my.zabiju.diblu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClenRodiny {

    private Person name;
    private boolean dostal = false;

    private ClenRodiny obdarovany;
    private List<Family> zakazaneRodiny;
    private List<Person> daval;


    public ClenRodiny(Person name, List<Person> daval, Family... families) {
        super();
        this.name = name;
        this.daval = daval;
        zakazaneRodiny = new ArrayList<>(Arrays.asList(families));
    }


    public Person name() {
        return name;
    }


    public boolean dostal() {
        return dostal;
    }

    public boolean davalMu(Person novy){
        return daval.contains(novy);
    }


    public List<Family> getZakazaneRodiny() {
        return zakazaneRodiny;
    }


    public void setDostal(boolean value) {
        this.dostal = value;

    }


    public void setObdarovany(ClenRodiny clen) {
        this.obdarovany = clen;
    }


    public ClenRodiny getObdarovany() {
        return obdarovany;
    }


    public String getEmail() {
        return "p.lastovka@seznam.cz";
    }

}
