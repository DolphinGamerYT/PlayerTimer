package playertimer.playertimer.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import playertimer.playertimer.PlayerTimer;

public class ShowTimeCommand implements CommandExecutor {

    private PlayerTimer plugin;

    public ShowTimeCommand(PlayerTimer plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("PlayerTimer.showtime")) { // PlayerTimer.showtime
                if (args.length == 1) {
                    Player user = Bukkit.getPlayer(args[0]);

                    if (user != null) {
                        p.sendMessage(ChatColor.YELLOW + user.getName() + " has " + processTime(plugin.getPlayersCounting().getTime(user)));
                    } else {
                        p.sendMessage(ChatColor.RED + "Please, specify a name of an Online Player.");
                    }
                } else if (args.length == 0) {
                    p.sendMessage(ChatColor.YELLOW + "You have " + processTime(plugin.getPlayersCounting().getTime(p)));
                } else {
                    p.sendMessage(ChatColor.RED + "Please, specify a name of an Online Player.");
                }
            }
        } else {
            if (args.length == 1) {
                Player p = Bukkit.getPlayer(args[0]);

                if (p != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + p.getName() + " has " + processTime(plugin.getPlayersCounting().getTime(p)));
                } else {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, specify a name of an Online Player.");
                }

            } else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Please, specify a name of an Online Player.");
            }
        }

        return false;
    }

    private String processTime(long time) {
        return (time/86400) + "d " + (time/3600) + "h " + (time/60) + "m " + (time%60) + "s";
    }

}
