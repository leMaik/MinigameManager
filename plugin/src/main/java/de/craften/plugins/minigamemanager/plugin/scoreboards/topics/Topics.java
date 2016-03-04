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
    private static final Topic ORDINAL_RANK = score -> {
        int rank = score.getRank();
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (rank % 100) {
            case 11:
            case 12:
            case 13:
                return rank + "th";
            default:
                return rank + suffixes[rank % 10];
        }
    };

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
