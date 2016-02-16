package de.craften.plugins.minigamemanager.plugin;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import de.craften.plugins.minigamemanager.MinigameApiFactory;
import de.craften.plugins.minigamemanager.api.MinigameApi;
import de.craften.plugins.minigamemanager.plugin.api.MinigameApiImpl;
import de.craften.plugins.minigamemanager.plugin.data.Score;
import de.craften.plugins.minigamemanager.plugin.persistence.MinigamePersistence;
import de.craften.plugins.minigamemanager.plugin.persistence.YamlMinigamePersistence;
import de.craften.plugins.minigamemanager.plugin.scoreboards.Scoreboard;
import de.craften.plugins.minigamemanager.plugin.scoreboards.SignScoreboard;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

/**
 * The MinigameManager plugin that manages scoreboard and configuration.
 * <p/>
 * If you want to integrate your minigame, look into {@link MinigameApi}.
 */
public class MinigameManagerPlugin extends JavaPlugin {
    private MinigamePersistence persistence;
    private Multimap<String, Scoreboard> scoreboards = ArrayListMultimap.create();

    @Override
    public void onEnable() {
        persistence = new YamlMinigamePersistence(new File(getDataFolder(), "scores.yml"));
        MinigameApi.setFactory(new MinigameApiFactory() {
            @Override
            public MinigameApi createApi(String id, String niceName) {
                return new MinigameApiImpl(MinigameManagerPlugin.this, id, niceName);
            }
        });

        ConfigurationSection scoreboardsConfig = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "scoreboards.yml"));
        for (String key : scoreboardsConfig.getKeys(false)) {
            ConfigurationSection config = scoreboardsConfig.getConfigurationSection(key);
            if (config.getString("type").equals("sign")) {
                SignScoreboard scoreboard = new SignScoreboard(config);
                scoreboards.put(scoreboard.getGameId(), scoreboard);
            }
        }

        for (String gameId : scoreboards.keySet()) {
            updateScoreboards(gameId);
        }
    }

    public MinigamePersistence getPersistence() {
        return persistence;
    }

    /**
     * Updates all scoreboards for the game with the given ID.
     *
     * @param gameId game ID
     */
    public void updateScoreboards(String gameId) {
        List<Score> highscore = getPersistence().getTopScores(gameId, 50);
        for (Scoreboard scoreboard : scoreboards.get(gameId)) {
            scoreboard.update(highscore);
        }
    }
}
