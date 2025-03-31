package model;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Tests for the ActiveTeam class.
 */
public class ActiveTeamTest {

  /**
   * Helper method to create a Player.
   *
   * @param firstName the player's first name
   * @param lastName the player's last name
   * @param pos the player's preferred position
   * @param skill the player's skill level
   * @return a new Player instance
   */
  private Player createPlayer(String firstName, String lastName, Position pos, int skill) {
    return new Player(firstName, lastName, LocalDate.of(2015, 5, 20), pos, skill);
  }

  /**
   * Tests that constructing a team with fewer than 10 players throws an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithTooFewPlayers() {
    List<Player> players = Arrays.asList(
        createPlayer("Alice", "Smith", Position.GOALIE, 5),
        createPlayer("Bob", "Johnson", Position.DEFENDER, 4)
    );
    new ActiveTeam(players);
  }

  /**
   * Tests that constructing a team with more than 20 players results in a team with exactly 20 players.
   */
  @Test
  public void testConstructorWithTooManyPlayers() {
    // Create 22 players.
    Player[] playerArray = new Player[22];
    for (int i = 0; i < 22; i++) {
      playerArray[i] = createPlayer("Player" + i, "LastName" + i, Position.MIDFIELDER, 5);
    }
    List<Player> players = Arrays.asList(playerArray);
    ActiveTeam team = new ActiveTeam(players);
    // The team should have exactly 20 players after removing the weakest ones.
    assertEquals(20, team.getAllPlayers().size());
  }

  /**
   * Tests adding a player to a full team and verifies that the weakest player is removed.
   */
  @Test
  public void testAddPlayerAndRemoveWeakestPlayer() {
    // Create 10 players with the same skill level.
    Player[] playerArray = new Player[10];
    for (int i = 0; i < 10; i++) {
      playerArray[i] = createPlayer("Player" + i, "LastName" + i, Position.MIDFIELDER, 5);
    }
    List<Player> players = Arrays.asList(playerArray);
    ActiveTeam team = new ActiveTeam(players);

    // Add 10 extra players to fill the team to 20.
    for (int i = 0; i < 10; i++) {
      team.addPlayer(createPlayer("Extra" + i, "Player", Position.DEFENDER, 3));
    }
    // Team size should be 20.
    assertEquals(20, team.getAllPlayers().size());

    // Adding one more player should trigger removal of the weakest player and keep the size at 20.
    team.addPlayer(createPlayer("New", "Player", Position.FORWARD, 4));
    assertEquals(20, team.getAllPlayers().size());
  }

  /**
   * Tests that jersey numbers are assigned correctly within the range 1-20 and are unique.
   */
  @Test
  public void testAssignJerseyNumbers() {
    // Create 10 players.
    Player[] playerArray = new Player[10];
    for (int i = 0; i < 10; i++) {
      playerArray[i] = createPlayer("Player" + i, "LastName" + i, Position.MIDFIELDER, 5);
    }
    List<Player> players = Arrays.asList(playerArray);
    ActiveTeam team = new ActiveTeam(players);
    team.assignJerseyNumbers();
    boolean[] assigned = new boolean[21]; // Indices 1 to 20.
    for (Player player : team.getAllPlayers()) {
      int number = player.getJerseyNumber();
      assertTrue("Jersey number out of range", number >= 1 && number <= 20);
      assertFalse("Duplicate jersey number assigned", assigned[number]);
      assigned[number] = true;
    }
  }

  /**
   * Tests choosing a starting lineup and verifies the lineup is sorted by position and last name.
   */
  @Test
  public void testChooseStartingLineupAndGetStartingLineup() {
    // Create 10 players with varying positions.
    Player[] playerArray = new Player[10];
    for (int i = 0; i < 10; i++) {
      Position pos;
      if (i == 0) {
        pos = Position.GOALIE;
      } else if (i <= 2) {
        pos = Position.DEFENDER;
      } else if (i <= 5) {
        pos = Position.MIDFIELDER;
      } else {
        pos = Position.FORWARD;
      }
      playerArray[i] = createPlayer("Player" + i, "LastName" + i, pos, 5);
    }
    List<Player> players = Arrays.asList(playerArray);
    ActiveTeam team = new ActiveTeam(players);
    team.assignJerseyNumbers();

    // Choose a starting lineup of 7 players.
    List<Player> startingLineup = Arrays.asList(
        playerArray[0], playerArray[1], playerArray[2],
        playerArray[3], playerArray[4], playerArray[5],
        playerArray[6]
    );
    team.chooseStartingLineup(startingLineup);
    List<Player> resultLineup = team.getStartingLineup();
    assertEquals(7, resultLineup.size());
    // Check that the first player in the sorted starting lineup has position GOALIE.
    assertEquals(Position.GOALIE, resultLineup.get(0).getPreferredPosition());
  }

  /**
   * Tests the replacement of a starting player with a bench player.
   */
  @Test
  public void testReplacePlayer() {
    // Create 10 players.
    Player[] playerArray = new Player[10];
    for (int i = 0; i < 10; i++) {
      playerArray[i] = createPlayer("Player" + i, "LastName" + i, Position.MIDFIELDER, 5);
    }
    List<Player> players = Arrays.asList(playerArray);
    ActiveTeam team = new ActiveTeam(players);
    team.assignJerseyNumbers();

    // Choose a starting lineup of 7 players.
    List<Player> startingLineup = Arrays.asList(
        playerArray[0], playerArray[1], playerArray[2],
        playerArray[3], playerArray[4], playerArray[5],
        playerArray[6]
    );
    team.chooseStartingLineup(startingLineup);

    // Replace the first starting player with a bench player (playerArray[7]).
    Player activePlayer = startingLineup.get(0);
    Player benchPlayer = playerArray[7];
    team.replacePlayer(activePlayer, benchPlayer);

    // Verify that the active player has been replaced.
    List<Player> newStartingLineup = team.getStartingLineup();
    assertTrue(newStartingLineup.contains(benchPlayer));
    assertFalse(newStartingLineup.contains(activePlayer));
  }
}