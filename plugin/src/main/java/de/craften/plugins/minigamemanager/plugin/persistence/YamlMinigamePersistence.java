package de.craften.plugins.minigamemanager.plugin.persistence;

import de.craften.plugins.minigamemanager.plugin.data.Score;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public void putScore(String gameId, Player player, String levelId, int score) {
        final String key = gameId + "." + levelId;
        final String scoresKey = key + ".scores";

        synchronized (lock) {
            if (!configuration.isConfigurationSection(key)) {
                configuration.createSection(key);
            }


            List<Map<?, ?>> scores = configuration.getMapList(scoresKey);
            if (scores == null) {
                scores = new ArrayList<>();
            }

            Map<String, String> scoreSection = new HashMap<>();
            scoreSection.put("player", player.getUniqueId().toString());
            scoreSection.put("score", String.valueOf(score));
            scores.add(scoreSection);

            configuration.set(scoresKey, scores);

            try {
                configuration.save(file);
            } catch (IOException e) {
                Bukkit.getLogger().severe("[MinigameManager] Could not save score");
            }
        }
    }

    @Override
    public List<Score> getTopScores(String gameId, String levelId, int count) {
        final String key = gameId + "." + levelId;
        final String scoresKey = key + ".scores";

        synchronized (lock) {
            if (!configuration.isList(scoresKey)) {
                return Collections.emptyList();
            }
            List<Map<?, ?>> scores = configuration.getMapList(scoresKey);
            List<Score> topScores = new ArrayList<>(scores.size());
            for (Map<?, ?> score : scores) {
                topScores.add(new Score(
                        gameId,
                        Bukkit.getOfflinePlayer(UUID.fromString(score.get("player").toString())),
                        Integer.parseInt(score.get("score").toString())
                ));
            }
            Collections.sort(topScores, new Comparator<Score>() {
                @Override
                public int compare(Score a, Score b) {
                    return b.getScore() - a.getScore();
                }
            });
            return topScores.subList(0, Math.min(count, topScores.size()));
        }
    }
}
