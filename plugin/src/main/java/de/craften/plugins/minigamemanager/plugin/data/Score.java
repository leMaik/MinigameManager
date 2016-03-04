package de.craften.plugins.minigamemanager.plugin.data;

import org.bukkit.OfflinePlayer;

/**
 * A score entry.
 */
public class Score {
    private final String gameId;
    private final OfflinePlayer player;
    private final int score;
    private final int rank;

    public Score(String gameId, OfflinePlayer player, int score, int rank) {
        this.gameId = gameId;
        this.player = player;
        this.score = score;
        this.rank = rank;
    }

    public String getGameId() {
        return gameId;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }
}
