package de.craften.plugins.minigamemanager.plugin.scoreboards.topics;

/**
 * Some static topics.
 *
 * @see Topic
 */
public final class Topics {
    private static final Topic EMPTY = score -> "";
    private static final Topic PLAYER_NAME = score -> score.getPlayer().getName();
    private static final Topic SCORE = score -> String.valueOf(score.getScore());
    private static final Topic TIME = score -> "n/a"; //TODO implement

    private static final Topic RANK = score -> String.valueOf(score.getRank());
    private static final Topic ORDINAL_RANK = score -> "n/a"; //TODO implement

    private Topics() {
    }

    public static Topic fromString(String str) {
        switch (str) {
            case "player.name":
                return PLAYER_NAME;
            case "score":
                return SCORE;
            case "rank":
                return RANK;
            case "rank.ordinal":
                return ORDINAL_RANK;
            case "time":
                return TIME;
            default:
                return EMPTY;
        }
    }
}
