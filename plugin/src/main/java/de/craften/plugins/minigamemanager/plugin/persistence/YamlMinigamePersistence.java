package de.craften.plugins.minigamemanager.plugin.persistence;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A minigame persistence that uses yaml files.
 */
public class YamlMinigamePersistence implements MinigamePersistence {
    private final File file;
    private final YamlConfiguration configuration;
    private final Object lock = new Object();

    public YamlMinigamePersistence(File file) {
        this.file = file;
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public void putScore(String gameId, Player player, int score) {
        synchronized (lock) {
            if (!configuration.isConfigurationSection(gameId)) {
                configuration.createSection(gameId);
            }

            List<Map<?, ?>> scores = configuration.getMapList(gameId + ".scores");
            if (scores == null) {
                scores = new ArrayList<>();
            }

            Map<String, String> scoreSection = new HashMap<>();
            scoreSection.put("player", player.getUniqueId().toString());
            scoreSection.put("score", String.valueOf(score));
            scores.add(scoreSection);

            configuration.set(gameId + ".scores", scores);

            try {
                configuration.save(file);
            } catch (IOException e) {
                Bukkit.getLogger().severe("[MinigameManager] Could not save score");
            }
        }
    }
}
