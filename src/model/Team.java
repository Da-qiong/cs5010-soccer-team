package model;

import java.util.List;

/**
 * Interface for a soccer team. Only a Coach is allowed to create a team.
 */
public interface Team {

    /**
     * Adds a player to the team. If the team already has 20 players, the weakest player is removed first.
     *
     * @param player the player to add
     */
    void addPlayer(Player player);

    /**
     * Removes the weakest player from the team.
     */
    void removeWeakestPlayer();

    /**
     * Assigns random and unique jersey numbers (1 to 20) to all players in the team.
     *
     * @throws IllegalStateException if the team size exceeds allowed limits
     */
    void assignJerseyNumbers();

    /**
     * Chooses the starting lineup (7 players) from the given list.
     *
     * @param startingLineup the list of players selected for the starting lineup
     * @throws IllegalArgumentException if the lineup does not contain exactly 7 players
     */
    void chooseStartingLineup(List<Player> startingLineup);

    /**
     * Returns a list of all players in the team, sorted alphabetically by last name.
     *
     * @return the list of all players
     */
    List<Player> getAllPlayers();

    /**
     * Returns the starting lineup, sorted by position order (GOALIE, DEFENDER, MIDFIELDER, FORWARD)
     * and alphabetically by last name within the same position.
     *
     * @return the starting lineup players
     */
    List<Player> getStartingLineup();

    /**
     * Replaces a player in the starting lineup (activePlayers) with a player from the bench.
     *
     * @param activePlayer the player from the starting lineup to be replaced
     * @param benchPlayer  the player from the bench to replace with
     * @throws IllegalArgumentException if the players are not found in their respective lists
     */
    void replacePlayer(Player activePlayer, Player benchPlayer);
}