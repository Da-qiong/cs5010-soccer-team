package model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

/**
 * Expanded tests for the Player class covering additional functionalities and edge cases.
 */
public class PlayerTest {

  /**
   * Tests valid Player creation and verifies getters.
   */
  @Test
  public void testPlayerCreationAndGetters() {
    LocalDate dob = LocalDate.of(2015, 5, 20);
    Player player = new Player("Alice", "Smith", dob, Position.GOALIE, 5);

    assertEquals("Alice", player.getFirstName());
    assertEquals("Smith", player.getLastName());
    assertEquals(dob, player.getDateOfBirth());
    assertEquals(Position.GOALIE, player.getPreferredPosition());
    assertEquals(5, player.getSkill());
    assertEquals(-1, player.getJerseyNumber());
  }

  /**
   * Tests Player creation with invalid skill levels and expects exceptions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCreationInvalidSkillLow() {
    new Player("Invalid", "Low", LocalDate.now(), Position.DEFENDER, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCreationInvalidSkillHigh() {
    new Player("Invalid", "High", LocalDate.now(), Position.FORWARD, 6);
  }

  /**
   * Tests all setter methods of the Player class.
   */
  @Test
  public void testPlayerSetters() {
    Player player = new Player("Alice", "Smith", LocalDate.of(2015, 5, 20), Position.GOALIE, 5);

    player.setFirstName("Alicia");
    player.setLastName("Brown");
    player.setPreferredPosition(Position.FORWARD);
    player.setSkill(3);
    player.setJerseyNumber(10);

    assertEquals("Alicia", player.getFirstName());
    assertEquals("Brown", player.getLastName());
    assertEquals(Position.FORWARD, player.getPreferredPosition());
    assertEquals(3, player.getSkill());
    assertEquals(10, player.getJerseyNumber());
  }

  /**
   * Tests the toString representation of Player.
   */
  @Test
  public void testToString() {
    Player player = new Player("Alice", "Smith", LocalDate.of(2015, 5, 20), Position.GOALIE, 5);
    player.setJerseyNumber(7);
    String expected = "Alice Smith (Jersey: 7, Skill: 5)";

    assertEquals(expected, player.toString());
  }

  /**
   * Tests the immutability of date of birth.
   */
  @Test
  public void testDateOfBirthImmutability() {
    LocalDate dob = LocalDate.of(2000, 1, 1);
    Player player = new Player("Immutable", "DOB", dob, Position.MIDFIELDER, 4);

    assertEquals(dob, player.getDateOfBirth());

    // Verify that changes to the original dob don't affect player's dob
    dob = dob.plusYears(1);
    assertNotEquals(dob, player.getDateOfBirth());
  }

  /**
   * Tests if jersey number can be reset properly.
   */
  @Test
  public void testJerseyNumberReset() {
    Player player = new Player("Jersey", "Reset", LocalDate.now(), Position.DEFENDER, 2);

    player.setJerseyNumber(5);
    assertEquals(5, player.getJerseyNumber());

    player.setJerseyNumber(-1); // Resetting jersey number
    assertEquals(-1, player.getJerseyNumber());
  }

  /**
   * Tests changing skill to invalid values through setter.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidSkillLevel() {
    Player player = new Player("Skill", "Test", LocalDate.now(), Position.FORWARD, 3);

    player.setSkill(6); // Invalid skill level
  }

  /**
   * Comprehensive test ensuring all fields can be updated and retrieved correctly.
   */
  @Test
  public void testComprehensivePlayerUpdate() {
    Player player = new Player("John", "Doe", LocalDate.of(1990, 6, 15), Position.DEFENDER, 3);

    player.setFirstName("Jonathan");
    player.setLastName("Dorian");
    player.setPreferredPosition(Position.MIDFIELDER);
    player.setSkill(4);
    player.setJerseyNumber(8);

    assertEquals("Jonathan", player.getFirstName());
    assertEquals("Dorian", player.getLastName());
    assertEquals(Position.MIDFIELDER, player.getPreferredPosition());
    assertEquals(4, player.getSkill());
    assertEquals(8, player.getJerseyNumber());

    String expectedString = "Jonathan Dorian (Jersey: 8, Skill: 4)";
    assertEquals(expectedString, player.toString());
  }
}
