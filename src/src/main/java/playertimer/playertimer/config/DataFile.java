package playertimer.playertimer.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import playertimer.playertimer.PlayerTimer;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class DataFile {

    private final PlayerTimer plugin;

    private YamlConfiguration defaultcfg;
    private File dataFile;

    public DataFile(PlayerTimer plugin) {
        this.plugin = plugin;
        setup();
    }

    public void setup() {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        dataFile = new File(plugin.getDataFolder(), "data.yml");

        if (!dataFile.exists()) {
            try {
                plugin.saveResource("data.yml", true);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Could not create data.yml file.");
            }
        }

        defaultcfg = YamlConfiguration.loadConfiguration(dataFile);

        plugin.getLogger().log(Level.FINE, "File data.yml loaded correctly.");

    }

    public YamlConfiguration getData() {
        return defaultcfg;
    }

    public File getFile() {
        return dataFile;
    }

    public void saveData() {
        try {
            defaultcfg.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadData() {
        if (dataFile.exists()) {
            defaultcfg = YamlConfiguration.loadConfiguration(dataFile);
        }
    }

    public void setTime(UUID id, long time) {
        getData().set(id.toString(), time);
    }

    public long getTime(Player p) {
        return getData().getLong(p.getUniqueId().toString());
    }

    public long getTime(UUID id) {
        return getData().getLong(id.toString());
    }

    public long getTime(String id) {
        return getData().getLong(UUID.fromString(id).toString());
    }

}

