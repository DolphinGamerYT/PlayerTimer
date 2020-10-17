package playertimer.playertimer.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import playertimer.playertimer.PlayerTimer;
import playertimer.playertimer.config.DataFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayersCounting {

    private PlayerTimer plugin;
    private DataFile dataFile;

    private ArrayList<UUID> players = new ArrayList<>();
    private ArrayList<UUID> registeredTimePlayers = new ArrayList<>();
    private HashMap<UUID, Integer> playersTime = new HashMap<>();

    private int timer = 0;
    private BukkitRunnable counter = new BukkitRunnable() {
        @Override
        public void run() {
            timer++;

            for (UUID player : players) {
                playersTime.put(player, playersTime.get(player)+1);
                if (!registeredTimePlayers.contains(player)) {
                    registeredTimePlayers.add(player);
                }
            }
            if (timer % 5 == 0) {
                for (int i = 0; i < playersTime.size(); i++) {
                    dataFile.setTime(registeredTimePlayers.get(i), playersTime.get(registeredTimePlayers.get(i)));
                }
                dataFile.saveData();
                dataFile.reloadData();
                registeredTimePlayers.clear();
                playersTime.clear();
            }
        }
    };

    public PlayersCounting(PlayerTimer plugin) {
        this.plugin = plugin;
        this.dataFile = plugin.getDataFile();

        counter.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }

    public void addPlayer(Player p) {
        if (!players.contains(p.getUniqueId())) {
            players.add(p.getUniqueId());
        }
    }

    public void removePlayer(Player p) {
        if (players.contains(p.getUniqueId())) {
            players.remove(p.getUniqueId());
        }
    }

    public long getTime(Player p) {
        return getTime(p.getUniqueId());
    }

    public long getTime(UUID id) {
        return dataFile.getTime(id)+playersTime.get(id);
    }


}
