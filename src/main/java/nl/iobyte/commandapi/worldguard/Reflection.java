package nl.iobyte.commandapi.worldguard;

import org.bukkit.Bukkit;
import java.util.HashMap;

public class Reflection {

    private static final HashMap<String, Class<?>> classes = new HashMap<>();
    private static final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    public static Class<?> getCraftBukkitClass(String name) {
        return getClass("org.bukkit.craftbukkit." + version + "." + name);
    }

    public static Class<?> getNMSClass(String name) {
        return getClass("net.minecraft.server." + version + "." + name);
    }

    public static Class<?> getClass(String path) {
        if(classes.containsKey(path))
            return classes.get(path);

        try {
            Class<?> clazz = Class.forName(path);
            classes.put(path, clazz);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            classes.put(path, null);
            return null;
        }
    }

}

