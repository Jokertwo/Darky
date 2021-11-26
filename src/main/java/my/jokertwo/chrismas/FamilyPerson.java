package my.jokertwo.chrismas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

@Data
public class FamilyPerson {

    private final People name;
    private boolean gotIt;
    private final String nick;
    private final List<Family> forbidenFamily;
    private final List<People> daval;

    public FamilyPerson(People name, List<People> daval, String nick, Family... families) {
        super();
        this.name = name;
        this.daval = daval;
        this.nick = nick;
        forbidenFamily = new ArrayList<>(Arrays.asList(families));
        gotIt = false;
    }


    public People name() {
        return name;
    }



    public boolean davalMu(People novy) {
        return daval.contains(novy);
    }


    public List<Family> getForbiddenFamily() {
        return forbidenFamily;
    }

}
