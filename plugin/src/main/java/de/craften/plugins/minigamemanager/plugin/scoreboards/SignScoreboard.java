package de.craften.plugins.minigamemanager.plugin.scoreboards;

import de.craften.plugins.minigamemanager.plugin.data.Score;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

/**
 * A scoreboard that uses signs.
 */
public class SignScoreboard implements Scoreboard {
    private final Location location;
    private final String gameId;

    public SignScoreboard(ConfigurationSection config) {
        this.location = new Location(
                Bukkit.getWorld(config.getString("world")),
                config.getDouble("x"),
                config.getDouble("y"),
                config.getDouble("z")
        );
        this.gameId = ((Sign) location.getBlock().getState()).getLine(1);
    }

    public String getGameId() {
        return gameId;
    }

    @Override
    public void update(List<Score> content) {
        Block block = location.getBlock();
        Topic topic = Topic.fromString(((Sign) block.getState()).getLine(2));

        block = block.getRelative(BlockFace.DOWN);
        int i = 0;
        while (block.getState() instanceof Sign) {
            Sign sign = (Sign) block.getState();
            for (int line = 0; line < 4; line++) {
                if (i < content.size()) {
                    sign.setLine(line, getEntry(content.get(i), topic));
                    i++;
                } else {
                    sign.setLine(line, "");
                }
            }
            sign.update(true);
            block = block.getRelative(BlockFace.DOWN);
        }
    }

    private static String getEntry(Score score, Topic topic) {
        switch (topic) {
            case PLAYER_NAME:
                return score.getPlayer().getName();
            case SCORE:
                return String.valueOf(score.getScore());
            case TIME:
                return "n/a"; //TODO
            default:
                return "";
        }
    }

    public enum Topic {
        EMPTY,
        PLAYER_NAME,
        SCORE,
        TIME;

        public static Topic fromString(String str) {
            switch (str) {
                case "player.name":
                    return PLAYER_NAME;
                case "score":
                    return SCORE;
                case "time":
                    return TIME;
                default:
                    return EMPTY;
            }
        }
    }
}
