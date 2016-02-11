package de.craften.plugins.minigamemanager.api;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * API for minigames.
 */
public class MinigameApi {
    private final String identifier;
    private final String name;

    public MinigameApi(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public static MinigameApi forPlugin(JavaPlugin plugin, String niceName) {
        return new MinigameApi(plugin.getName(), niceName);
    }
}
