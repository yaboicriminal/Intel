package lol.myles.intel.commands;

import lol.myles.intel.Intel;
import lol.myles.intel.managers.ConfigManager;
import lol.myles.intel.utils.command.Command;
import lol.myles.intel.utils.command.CommandArgs;
import lol.myles.intel.utils.command.CommandFramework;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by VBS Aka TheS7W.
 * Package name: lol.myles.intel.commands
 * Date: 15/05/2016
 * Project name: Intel
 */
public class SetSpawnCommand {

    Intel main = Intel.getInstance();
    ConfigManager cm = main.getConfigManager();
    CommandFramework commandFramework = main.getCommandFramework();

    public SetSpawnCommand() {
        commandFramework.registerCommands(this);
    }

    @Command(name = "setspawn", inGameOnly = true, permission = "intel.setspawn")
    public void execute(CommandArgs sender) {
        Player player = sender.getPlayer();
        cm.setSpawnLocation(player);
        player.sendMessage(ChatColor.GREEN + "Spawn has been set!");
    }

}
