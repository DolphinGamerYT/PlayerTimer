package playertimer.playertimer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import playertimer.playertimer.commands.ShowTimeCommand;
import playertimer.playertimer.config.DataFile;
import playertimer.playertimer.core.PlayersCounting;
import playertimer.playertimer.listener.MainListener;

import java.util.logging.Level;

public final class PlayerTimer extends JavaPlugin {

    private PlayerTimer instance;

    private DataFile dataFile;

    private PlayersCounting playersCounting;

    @Override
    public void onEnable() {
        Bukkit.getServer().getLogger().log(Level.INFO, "Loading AFKPlugin...");

        this.instance = this;

        dataFile = new DataFile(this);
        playersCounting = new PlayersCounting(this);

        Bukkit.getPluginManager().registerEvents(new MainListener(this), this);

        getCommand("showtime").setExecutor(new ShowTimeCommand(this));

        Bukkit.getServer().getLogger().log(Level.INFO, "AFKPlugin loaded correctly!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerTimer getInstance() {
        return instance;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    public PlayersCounting getPlayersCounting() {
        return playersCounting;
    }

}
