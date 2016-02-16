package de.craften.plugins.minigamemanager.plugin.persistence;

import org.bukkit.entity.Player;

/**
 * A persistence for minigames. Stores highscores and other data.
 */
public interface MinigamePersistence {
    /**
     * Stores a score for a player in the given game.
     *
     * @param gameId game ID
     * @param player player
     * @param score  score
     */
    void putScore(String gameId, Player player, int score);
}
