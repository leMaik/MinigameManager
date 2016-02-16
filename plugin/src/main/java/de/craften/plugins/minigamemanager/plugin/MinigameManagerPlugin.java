package de.craften.plugins.minigamemanager.plugin;

import de.craften.plugins.minigamemanager.MinigameApiFactory;
import de.craften.plugins.minigamemanager.api.MinigameApi;
import de.craften.plugins.minigamemanager.plugin.api.MinigameApiImpl;
import de.craften.plugins.minigamemanager.plugin.persistence.MinigamePersistence;
import de.craften.plugins.minigamemanager.plugin.persistence.YamlMinigamePersistence;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * The MinigameManager plugin that manages scoreboard and configuration.
 * <p/>
 * If you want to integrate your minigame, look into {@link MinigameApi}.
 */
public class MinigameManagerPlugin extends JavaPlugin {
    private MinigamePersistence persistence;

    @Override
    public void onEnable() {
        persistence = new YamlMinigamePersistence(new File(getDataFolder(), "scores.yml"));
        MinigameApi.setFactory(new MinigameApiFactory() {
            @Override
            public MinigameApi createApi(String id, String niceName) {
                return new MinigameApiImpl(MinigameManagerPlugin.this, id, niceName);
            }
        });
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
        //TODO
    }
}
