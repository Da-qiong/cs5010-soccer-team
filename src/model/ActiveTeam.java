package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * A simple implementation of the Team interface.
 * This class manages players, starting lineup, and bench players.
 */
public class ActiveTeam implements Team {
    private List<Player> players;
    private List<Player> activePlayers;
    private List<Player> benchPlayers;
    private List<Player> startingLineup;

    // Minimum and maximum allowed team sizes.
    private final int TEAM_SIZE_MIN = 10;
    private final int TEAM_SIZE_MAX = 20;

    /**
     * Constructs an empty team. Only a coach can create a team.
     */
    public ActiveTeam() {
        this.players = new ArrayList<>();
        this.activePlayers = new ArrayList<>();
        this.benchPlayers = new ArrayList<>();
        this.startingLineup = new ArrayList<>();
    }

    /**
     * Constructs a team with an initial list of players.
     * If the number of players is less than 10, an exception is thrown.
     * If the number of players exceeds 20, the weakest players are removed until only 20 remain.
     *
     * @param players the initial list of players
     * @throws IllegalArgumentException if there are fewer than 10 players
     */
    public ActiveTeam(List<Player> players) {
        if (players.size() < TEAM_SIZE_MIN) {
            throw new IllegalArgumentException("Team must have at least 10 players.");
        }
        if (players.size() > TEAM_SIZE_MAX) {
            players = new ArrayList<>(players);
            while (players.size() > TEAM_SIZE_MAX) {
                Player weakest = Collections.min(players, Comparator.comparingInt(Player::getSkill));
                players.remove(weakest);
            }
        }
        this.players = new ArrayList<>(players);
        this.activePlayers = new ArrayList<>();
        this.benchPlayers = new ArrayList<>();
        this.startingLineup = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPlayer(Player player) {
        if (players.size() >= TEAM_SIZE_MAX) {
            removeWeakestPlayer();
        }
        players.add(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeWeakestPlayer() {
        if (players.isEmpty()) {
            return;
        }
        Player weakest = Collections.min(players, Comparator.comparingInt(Player::getSkill));
        players.remove(weakest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void assignJerseyNumbers() {
        if (players.size() > TEAM_SIZE_MAX) {
            throw new IllegalStateException("Too many players to assign jersey numbers.");
        }
        List<Integer> availableNumbers = new ArrayList<>();
        for (int i = 1; i <= TEAM_SIZE_MAX; i++) {
            availableNumbers.add(i);
        }
        Collections.shuffle(availableNumbers, new Random());
        for (Player player : players) {
            player.setJerseyNumber(availableNumbers.remove(0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void chooseStartingLineup(List<Player> lineup) {
        if (lineup.size() != 7) {
            throw new IllegalArgumentException("Starting lineup must contain exactly 7 players.");
        }
        startingLineup.clear();
        startingLineup.addAll(lineup);

        // Sort the starting lineup: first by position order (GOALIE, DEFENDER, MIDFIELDER, FORWARD),
        // then alphabetically by last name within the same position.
        startingLineup.sort(new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                int pos1 = getPositionOrder(p1.getPreferredPosition());
                int pos2 = getPositionOrder(p2.getPreferredPosition());
                if (pos1 != pos2) {
                    return Integer.compare(pos1, pos2);
                }
                return p1.getLastName().compareTo(p2.getLastName());
            }

            private int getPositionOrder(Position pos) {
                switch (pos) {
                    case GOALIE:
                        return 1;
                    case DEFENDER:
                        return 2;
                    case MIDFIELDER:
                        return 3;
                    case FORWARD:
                        return 4;
                    default:
                        return 5;
                }
            }
        });

        // Update active players and bench players lists.
        activePlayers.clear();
        benchPlayers.clear();
        activePlayers.addAll(startingLineup);
        for (Player player : players) {
            if (!activePlayers.contains(player)) {
                benchPlayers.add(player);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getAllPlayers() {
        List<Player> sorted = new ArrayList<>(players);
        sorted.sort((p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));
        return sorted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Player> getStartingLineup() {
        return new ArrayList<>(startingLineup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void replacePlayer(Player activePlayer, Player benchPlayer) {
        if (!activePlayers.contains(activePlayer)) {
            throw new IllegalArgumentException("The active player is not in the starting lineup.");
        }
        if (!benchPlayers.contains(benchPlayer)) {
            throw new IllegalArgumentException("The bench player is not available.");
        }

        // Replace in the activePlayers list.
        int index = activePlayers.indexOf(activePlayer);
        activePlayers.set(index, benchPlayer);

        benchPlayers.remove(benchPlayer);
        benchPlayers.add(activePlayer);

        // Synchronize the starting lineup.
        int lineupIndex = startingLineup.indexOf(activePlayer);
        if (lineupIndex != -1) {
            startingLineup.set(lineupIndex, benchPlayer);
        }
    }
}