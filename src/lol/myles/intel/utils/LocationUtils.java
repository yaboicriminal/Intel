package lol.myles.intel.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

/**
 * Created by S7W on 15/04/2016.
 * Project name: Icarus
 * Package name: lol.myles.icarus.utils
 */
public class LocationUtils {

    public static String serializeLocation(final Location l) {
        String s = "";
        s = s + "@w;" + l.getWorld().getName();
        s = s + ":@x;" + l.getX();
        s = s + ":@y;" + l.getY();
        s = s + ":@z;" + l.getZ();
        s = s + ":@p;" + l.getPitch();
        s = s + ":@ya;" + l.getYaw();
        return s;
    }

    public static Location deserializeLocation(final String s) {
        final Location l = new Location((World) Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0);
        final String[] arr$;
        final String[] att = arr$ = s.split(":");
        for (int len$ = att.length, i$ = 0; i$ < len$; ++i$) {
            final String attribute = arr$[i$];
            final String[] split = attribute.split(";");
            if (split[0].equalsIgnoreCase("@w")) {
                l.setWorld(Bukkit.getWorld(split[1]));
            }
            if (split[0].equalsIgnoreCase("@x")) {
                l.setX(Double.parseDouble(split[1]));
            }
            if (split[0].equalsIgnoreCase("@y")) {
                l.setY(Double.parseDouble(split[1]));
            }
            if (split[0].equalsIgnoreCase("@z")) {
                l.setZ(Double.parseDouble(split[1]));
            }
            if (split[0].equalsIgnoreCase("@p")) {
                l.setPitch(Float.parseFloat(split[1]));
            }
            if (split[0].equalsIgnoreCase("@ya")) {
                l.setYaw(Float.parseFloat(split[1]));
            }
        }
        return l;
    }
}