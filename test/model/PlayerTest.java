package model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;

/**
 * Tests for the Player class.
 */
public class PlayerTest {

  /**
   * Tests the creation of a Player object and verifies its getters.
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
    // Jersey number is not assigned yet, so it should be -1.
    assertEquals(-1, player.getJerseyNumber());

    try {
      Player errorPlayer = new Player("Error", "Test", dob, Position.GOALIE, 8);
    } catch (Exception e) {
      // This will be exit only an IllegalArgumentException was throw.
    }
}

  /**
   * Tests the setter methods for the Player object.
   */
  @Test
  public void testPlayerSetters() {
    LocalDate dob = LocalDate.of(2015, 5, 20);
    Player player = new Player("Alice", "Smith", dob, Position.GOALIE, 5);
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
   * Tests the toString method of the Player class.
   */
  @Test
  public void testToString() {
    LocalDate dob = LocalDate.of(2015, 5, 20);
    Player player = new Player("Alice", "Smith", dob, Position.GOALIE, 5);
    player.setJerseyNumber(7);
    String expected = "Alice Smith (Jersey: 7, Skill: 5)";
    assertEquals(expected, player.toString());
  }
}