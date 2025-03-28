package model;

import java.time.LocalDate;

/**
 * Represents a player in the soccer team. Implements the Person interface.
 */
public class Player implements Person {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Position preferredPosition;
    private int skill;         // Skill level from 1 (lowest) to 5 (highest)
    private int jerseyNumber;  // Jersey number, assigned when the team is created

    /**
     * Constructs a new Player.
     *
     * @param firstName         the player's first name
     * @param lastName          the player's last name
     * @param dateOfBirth       the player's date of birth
     * @param preferredPosition the player's preferred position
     * @param skill             the player's skill level (1-5)
     */
    public Player(String firstName, String lastName, LocalDate dateOfBirth,
        Position preferredPosition, int skill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.preferredPosition = preferredPosition;
        this.skill = skill;
        this.jerseyNumber = -1; // Not yet assigned
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the preferred position of the player.
     *
     * @return the preferred position
     */
    public Position getPreferredPosition() {
        return preferredPosition;
    }

    /**
     * Sets the preferred position of the player.
     *
     * @param preferredPosition the new preferred position
     */
    public void setPreferredPosition(Position preferredPosition) {
        this.preferredPosition = preferredPosition;
    }

    /**
     * Returns the skill level of the player.
     *
     * @return the skill level
     */
    public int getSkill() {
        return skill;
    }

    /**
     * Sets the skill level of the player.
     *
     * @param skill the new skill level
     */
    public void setSkill(int skill) {
        this.skill = skill;
    }

    /**
     * Returns the jersey number of the player.
     *
     * @return the jersey number
     */
    public int getJerseyNumber() {
        return jerseyNumber;
    }

    /**
     * Sets the jersey number of the player.
     *
     * @param jerseyNumber the new jersey number
     */
    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Jersey: " + jerseyNumber + ")";
    }
}
