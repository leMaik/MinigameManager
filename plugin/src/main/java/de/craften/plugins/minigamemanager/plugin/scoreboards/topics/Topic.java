package de.craften.plugins.minigamemanager.plugin.scoreboards.topics;

import de.craften.plugins.minigamemanager.plugin.data.Score;

/**
 * A topic for a ranking column. Produces a string to put onto a line.
 */
public interface Topic {
    /**
     * Gets the entry for this topic for the given score.
     *
     * @param score score
     * @return entry for this topic for that score
     */
    String getEntry(Score score);
}
