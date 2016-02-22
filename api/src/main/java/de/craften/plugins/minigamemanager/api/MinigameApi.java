package de.craften.plugins.minigamemanager.api;

import de.craften.plugins.minigamemanager.MinigameApiFactory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * API for minigames.
 */
public abstract class MinigameApi {
    private static MinigameApiFactory factory;

    public static boolean isAvailable() {
        return factory != null;
    }

    public static MinigameApi forPlugin(JavaPlugin plugin, String niceName) {
        return factory.createApi(plugin.getName(), niceName);
    }

    public static void setFactory(MinigameApiFactory factory) {
        MinigameApi.factory = factory;
    }

    /**
     * Sets the score for the given player in the given level.
     *
     * @param player  player
     * @param levelId an identifier for the level, i.e. arena name or level name; must be unique per game
     * @param score   score
     */
    public abstract void setScore(Player player, String levelId, int score);
}
