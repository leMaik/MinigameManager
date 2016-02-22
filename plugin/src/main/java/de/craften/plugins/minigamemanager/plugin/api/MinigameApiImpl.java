package de.craften.plugins.minigamemanager.plugin.api;

import de.craften.plugins.minigamemanager.api.MinigameApi;
import de.craften.plugins.minigamemanager.plugin.MinigameManagerPlugin;
import org.bukkit.entity.Player;

/**
 * API for minigames.
 */
public class MinigameApiImpl extends MinigameApi {
    private MinigameManagerPlugin plugin;
    private final String identifier;
    private final String name;

    public MinigameApiImpl(MinigameManagerPlugin plugin, String identifier, String name) {
        this.plugin = plugin;
        this.identifier = identifier;
        this.name = name;
    }

    @Override
    public void setScore(Player player, String levelId, int score) {
        plugin.getPersistence().putScore(identifier, player, levelId, score);
        plugin.updateScoreboards(identifier,levelId);
    }
}
