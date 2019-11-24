package my.zabiju.diblu;

import java.util.List;


public interface Osoba {

    String name();


    /**
     * Jestli osoba u6 dostala dar
     * 
     * @return
     */
    boolean dostal();


    /**
     * Jestli osoba uz nekomu dava
     * 
     * @return
     */
    boolean dava();


    /**
     * Vrati vsechny yakazan0 rodiny
     * 
     * @return
     */
    List<Family> getZakazaneRodiny();


    void setDava(boolean value);


    void setDostal(boolean value);
}
