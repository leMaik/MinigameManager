package de.craften.plugins.minigamemanager.plugin.scoreboards;

import de.craften.plugins.minigamemanager.plugin.data.Score;
import de.craften.plugins.minigamemanager.plugin.scoreboards.topics.Topic;
import de.craften.plugins.minigamemanager.plugin.scoreboards.topics.Topics;
import de.craften.plugins.minigamemanager.plugin.util.Tuple;
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
    private final Tuple<String, String> gameAndLevel;

    public SignScoreboard(ConfigurationSection config) {
        this.location = new Location(
                Bukkit.getWorld(config.getString("world")),
                config.getDouble("x"),
                config.getDouble("y"),
                config.getDouble("z")
        );

        Sign sign = (Sign) location.getBlock().getState();
        this.gameAndLevel = Tuple.of(sign.getLine(1), sign.getLine(2));
    }

    @Override
    public void update(List<Score> content) {
        Block block = location.getBlock();
        Topic topic = Topics.fromString(((Sign) block.getState()).getLine(2));

        block = block.getRelative(BlockFace.DOWN);
        int i = 0;
        while (block.getState() instanceof Sign) {
            Sign sign = (Sign) block.getState();
            for (int line = 0; line < 4; line++) {
                if (i < content.size()) {
                    sign.setLine(line, topic.getEntry(content.get(i)));
                    i++;
                } else {
                    sign.setLine(line, "");
                }
            }
            sign.update(true);
            block = block.getRelative(BlockFace.DOWN);
        }
    }

    public Tuple<String, String> getTopic() {
        return gameAndLevel;
    }
}
