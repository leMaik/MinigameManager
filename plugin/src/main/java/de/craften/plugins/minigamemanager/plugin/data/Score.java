package de.craften.plugins.minigamemanager.plugin.data;

import org.bukkit.OfflinePlayer;

/**
 * A score entry.
 */
public class Score {
    private final String gameId;
    private final OfflinePlayer player;
    private final int score;

    public Score(String gameId, OfflinePlayer player, int score) {
        this.gameId = gameId;
        this.player = player;
        this.score = score;
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
}
