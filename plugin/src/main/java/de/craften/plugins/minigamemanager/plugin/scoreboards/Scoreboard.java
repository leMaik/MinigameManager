package de.craften.plugins.minigamemanager.plugin.scoreboards;

import de.craften.plugins.minigamemanager.plugin.data.Score;

import java.util.List;

/**
 * A scoreboard.
 */
public interface Scoreboard {
    /**
     * Updates the content of this scoreboard.
     *
     * @param content new content of this scoreboard
     */
    void update(List<Score> content);
}
