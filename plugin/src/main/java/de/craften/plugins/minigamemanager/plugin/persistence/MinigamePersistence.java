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
     * @param gameId  game ID
     * @param player  player
     * @param levelId an identifier for the level, i.e. arena name or level name; must be unique per game
     * @param score   score
     */
    void putScore(String gameId, Player player, String levelId, int score);

    /**
     * Gets the top scores of the given game.
     *
     * @param gameId  game ID
     * @param levelId an identifier for the level, i.e. arena name or level name; must be unique per game
     * @param count   maximum amount of top scores to get
     */
    List<Score> getTopScores(String gameId, String levelId, int count);
}
