package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.ActiveTeam;
import model.Player;
import model.Position;

/**
 * Demo class to simulate usage of ActiveTeam and Player functionality.
 */
public class Main {

  /**
   * Creates a Player instance with sample birth date.
   *
   * @param fn    the first name
   * @param ln    the last name
   * @param pos   the preferred position
   * @param skill the skill level (1–5)
   * @return a new Player object
   */
  private static Player createPlayer(String fn, String ln, Position pos, int skill) {
    return new Player(fn, ln, LocalDate.of(2015, 5, 20), pos, skill);
  }

  /**
   * Main method to demonstrate core functionality of the team management system.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    System.out.println("=== Running Demo ===");

    // Create 10 players with same skill and position
    List<Player> players = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      int randomSkill = ThreadLocalRandom.current().nextInt(1, 6); // [1, 6)
      players.add(createPlayer("Player" + i, "LastName" + i, Position.MIDFIELDER, randomSkill));
    }

    // Create a team with the players
    ActiveTeam team = new ActiveTeam(players);

    // Assign jersey numbers randomly
    team.assignJerseyNumbers();

    // Print all players
    System.out.println("All Players:");
    for (Player p : team.getAllPlayers()) {
      System.out.println(p);
    }

    // Choose the first 7 players as starting lineup
    List<Player> lineup = players.subList(0, 7);
    team.chooseStartingLineup(lineup);

    // Print starting lineup
    System.out.println("\nStarting Lineup:");
    for (Player p : team.getStartingLineup()) {
      System.out.println(p);
    }

    // Replace the first starter with a bench player
    Player toReplace = lineup.get(0);
    Player bench = players.get(7);
    team.replacePlayer(toReplace, bench);

    // Print new starting lineup after substitution
    System.out.println("\nAfter Substitution:");
    for (Player p : team.getStartingLineup()) {
      System.out.println(p);
    }
  }
}
