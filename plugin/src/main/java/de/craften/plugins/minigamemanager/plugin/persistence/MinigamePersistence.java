package de.craften.plugins.minigamemanager.plugin.persistence;

import de.craften.plugins.minigamemanager.plugin.data.Score;
import org.bukkit.entity.Player;

import java.util.List;

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

    /**
     * Gets the top scores of the given game.
     *
     * @param gameId game ID
     * @param count  maximum amount of top scores to get
     */
    List<Score> getTopScores(String gameId, int count);
}
