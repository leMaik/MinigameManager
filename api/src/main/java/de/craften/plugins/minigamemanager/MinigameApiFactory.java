package de.craften.plugins.minigamemanager;

import de.craften.plugins.minigamemanager.api.MinigameApi;

/**
 * A factory for minigame implementations.
 */
public interface MinigameApiFactory {
    /**
     * Gets the API instance for the game with the given ID and name.
     *
     * @param id       ID of the game
     * @param niceName name of the game
     * @return API instance for the game
     */
    MinigameApi createApi(String id, String niceName);
}
