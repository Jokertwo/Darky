package my.jokertwo.chrismas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FamilyPerson implements Person {

    private final People name;
    private boolean gotIt;
    private final List<Family> forbidenFamily;
    private final List<People> daval;


    public FamilyPerson(People name, List<People> daval, Family... families) {
        super();
        this.name = name;
        this.daval = daval;
        forbidenFamily = new ArrayList<>(Arrays.asList(families));
        gotIt = false;
    }


    public People name() {
        return name;
    }

    @Override
    public boolean alreadyGotten() {
        return gotIt;
    }

    @Override
    public void setGotten(boolean value) {
        this.gotIt = value;
    }

    @Override
    public void setGiving(boolean value) {

    }

    @Override
    public boolean isAlreadyGiving() {
        return false;
    }


    public boolean davalMu(People novy) {
        return daval.contains(novy);
    }


    public List<Family> getForbiddenFamily() {
        return forbidenFamily;
    }


}
