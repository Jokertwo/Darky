package my.jokertwo.chrismas;

import java.util.List;


public interface Person {

    People name();


    /**
     * @return return true if person already gotten
     */
    boolean alreadyGotten();


    /**
     * @return Return true if person already giving
     */
    boolean isAlreadyGiving();


    /**
     * @return Return list of forbidden families
     */
    List<Family> getForbiddenFamily();


    /**
     * Set information if person giving to someone
     *
     * @param value
     */
    void setGiving(boolean value);


    /**
     * Set information if person got present
     *
     * @param value
     */
    void setGotten(boolean value);
}
