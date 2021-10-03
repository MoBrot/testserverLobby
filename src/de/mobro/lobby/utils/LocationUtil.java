package de.mobro.lobby.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationUtil {

    public static File f = new File("plugins/Lobby" , "locations.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

    @SuppressWarnings("unused")
    private String locc;

    public LocationUtil(String location) {
        this.locc = location;
    }

    public static YamlConfiguration getConfig() {
        return cfg;
    }

    public static void saveLocation(String path, Location loc) {
        cfg.set(path + ".X", loc.getX());
        cfg.set(path + ".Y", loc.getY());
        cfg.set(path + ".Z", loc.getZ());
        cfg.set(path + ".Yaw", loc.getYaw());
        cfg.set(path + ".Pitch", loc.getPitch());
        cfg.set(path + ".World", loc.getWorld().getName());
        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveHeight(String path, double d) {
        cfg.set(path + ".Height", d);
        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLocation(String path) {
        cfg.set(path + ".X", null);
        cfg.set(path + ".Y", null);
        cfg.set(path + ".Z", null);
        cfg.set(path + ".Yaw", null);
        cfg.set(path + ".Pitch", null);
        cfg.set(path + ".World", null);
        try {
            cfg.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static double getHeight(String path) {
        return cfg.getDouble(path + ".Height");
    }

    public static boolean isSet(String path) {
        if((cfg.isSet(path + ".Y")) &&
                (cfg.isSet(path + ".Z") &&
                        (cfg.isSet(path + ".X") &&
                                (cfg.isSet(path + ".Yaw")) &&
                                (cfg.isSet(path + ".Pitch")) &&
                                (cfg.isSet(path + ".World"))))) {
            return true;
        }
        return false;
    }

    public static Location getLocation(String path) {
        String world = cfg.getString(path + ".World");
        double x = cfg.getDouble(path + ".X");
        double y = cfg.getDouble(path + ".Y");
        double z = cfg.getDouble(path + ".Z");
        double yaw = cfg.getDouble(path + ".Yaw");
        double pitch = cfg.getDouble(path + ".Pitch");
        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        loc.setYaw((float) yaw);
        loc.setPitch((float) pitch);
        return loc;
    }
}
