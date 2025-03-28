package model;

import java.time.LocalDate;

/**
 * An interface representing a person with basic attributes.
 */
public interface Person {

    /**
     * Returns the first name of the person.
     *
     * @return the first name
     */
    String getFirstName();

    /**
     * Returns the last name of the person.
     *
     * @return the last name
     */
    String getLastName();

    /**
     * Returns the date of birth of the person.
     *
     * @return the date of birth
     */
    LocalDate getDateOfBirth();

    /**
     * Sets the first name of the person.
     *
     * @param firstName the new first name
     */
    void setFirstName(String firstName);

    /**
     * Sets the last name of the person.
     *
     * @param lastName the new last name
     */
    void setLastName(String lastName);
}
