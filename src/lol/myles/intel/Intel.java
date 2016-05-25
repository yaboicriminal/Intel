package lol.myles.intel;

import lol.myles.intel.commands.SetSpawnCommand;
import lol.myles.intel.listeners.PlayerListeners;
import lol.myles.intel.managers.ConfigManager;
import lol.myles.intel.utils.command.Command;
import lol.myles.intel.utils.command.CommandFramework;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by VBS Aka TheS7W.
 * Package name: lol.myles.intel
 * Date: 14/05/2016
 * Project name: Intel
 */
public class Intel extends JavaPlugin {

    private static Intel instance;
    private ConfigManager configManager;
    private CommandFramework commandFramework;
    private Scoreboard scoreboard;

    @Override
    public void onEnable() {
        instance = this;
        configManager = new ConfigManager();
        configManager.setup();
        commandFramework = new CommandFramework(this);
        commandFramework.registerHelp();
        new SetSpawnCommand();
        new PlayerListeners();

        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        final Team owner = getExistingOrCreateNewTeam("owner", scoreboard, "" + ChatColor.DARK_RED);
        final Team dev = getExistingOrCreateNewTeam("dev", scoreboard, "" + ChatColor.DARK_AQUA);
        final Team admin = getExistingOrCreateNewTeam("admin", scoreboard, "" + ChatColor.RED);
        final Team mod = getExistingOrCreateNewTeam("mod", scoreboard, "" + ChatColor.ITALIC);
        final Team modplus = getExistingOrCreateNewTeam("mod+", scoreboard, ChatColor.DARK_PURPLE.toString() + ChatColor.ITALIC.toString());
        final Team elite = getExistingOrCreateNewTeam("elite3", scoreboard, "" + ChatColor.LIGHT_PURPLE.toString() + ChatColor.ITALIC.toString());
        final Team premium = getExistingOrCreateNewTeam("premium", scoreboard, "" + ChatColor.AQUA);
        final Team basic = getExistingOrCreateNewTeam("basic", scoreboard, "" + ChatColor.GREEN);
        final Team normal = getExistingOrCreateNewTeam("default", scoreboard, "" + ChatColor.RESET);
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(player.hasPermission("intel.owner")) {
                        if(!owner.hasPlayer(player)) {
                            owner.addPlayer(player);
                        }
                    } else if(player.hasPermission("intel.developer")) {
                        if (!dev.hasPlayer(player)) {
                            dev.addPlayer(player);
                        }
                    } else if(player.hasPermission("intel.admin")) {
                        if (!admin.hasPlayer(player)) {
                            admin.addPlayer(player);
                        }
                    }  else if(player.hasPermission("intel.moderator")) {
                        if (!mod.hasPlayer(player)) {
                            mod.addPlayer(player);
                        }
                    } else if(player.hasPermission("intel.elite")) {
                        if (!elite.hasPlayer(player)) {
                            elite.addPlayer(player);
                        }
                    }  else if(player.hasPermission("intel.premium")) {
                        if (!premium.hasPlayer(player)) {
                            premium.addPlayer(player);
                        }
                    } else if(player.hasPermission("intel.basic")) {
                        if (!basic.hasPlayer(player)) {
                            basic.addPlayer(player);
                        }
                    } else {
                        if(!normal.hasPlayer(player)) {
                            normal.addPlayer(player);
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(this, 20L, 20L);
    }

    private Team getExistingOrCreateNewTeam(String string, Scoreboard scoreboard, String prefix) {
        Team toReturn = scoreboard.getTeam(string);

        if (toReturn == null) {
            toReturn = scoreboard.registerNewTeam(string);
            toReturn.setPrefix(prefix + "");
        }

        return toReturn;
    }

    public static Intel getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public CommandFramework getCommandFramework() {
        return commandFramework;
    }
}
