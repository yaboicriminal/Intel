package lol.myles.intel.managers;

import lol.myles.intel.Intel;
import lol.myles.intel.utils.LocationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by VBS Aka TheS7W.
 * Package name: lol.myles.intel.managers
 * Date: 14/05/2016
 * Project name: Intel
 */
public class ConfigManager {

    Intel main = Intel.getInstance();
    File file;
    YamlConfiguration configuration;


    public void setup() {
        main.saveDefaultConfig();
        file = new File(main.getDataFolder(), "config.yml");
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public Location getSpawnLocation() {
        Location location = LocationUtils.deserializeLocation(configuration.getString("spawn"));
        return location;
    }

    public void setSpawnLocation(Player player) {
        configuration.set("spawn", LocationUtils.serializeLocation(player.getLocation()));
        save();
    }

    public int getBelowY() {
        return configuration.getInt("below");
    }

    private void save() {
        try {
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
