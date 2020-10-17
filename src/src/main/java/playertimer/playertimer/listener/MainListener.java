package playertimer.playertimer.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import playertimer.playertimer.PlayerTimer;

public class MainListener implements Listener {

    private PlayerTimer plugin;

    public MainListener(PlayerTimer plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.getPlayersCounting().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        plugin.getPlayersCounting().removePlayer(e.getPlayer());
    }

}
