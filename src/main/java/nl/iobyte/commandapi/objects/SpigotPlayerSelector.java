package nl.iobyte.commandapi.objects;

import nl.iobyte.commandapi.worldguard.WGManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use selectors in commands!
 *
 * @author Mindgamesnl
 *
 * Code from
 * https://github.com/Mindgamesnl/OpenAudioMc/blob/master/plugin/src/main/java/com/craftmend/openaudiomc/spigot/modules/players/objects/SpigotPlayerSelector.java
 */
public class SpigotPlayerSelector {

    private final String selector;

    public SpigotPlayerSelector(String selector) {
        this.selector = selector;
    }

    /**
     * this turns selectors like @a[r=5] into a usable list, since
     * 1.13 spigot removed this feature, FOR SOME REASON.. thanks guys..
     *
     * @param commandSender the sender
     * @return players following the selector
     */
    public List<Player> getPlayers(CommandSender commandSender) {
        List<Player> players = new ArrayList<>();

        if (selector.startsWith("@p")) {
            //get Location
            Location standPoint = getLocation(commandSender);

            if (getArgument("r").length() != 0) {
                int radius = Integer.parseInt(getArgument("r"));
                Player nearest = Bukkit.getOnlinePlayers().stream()
                        .filter(player -> player.getLocation().getWorld().getName().equals(standPoint.getWorld().getName()))
                        .min(Comparator.comparing(player -> player.getLocation().distance(standPoint)))
                        .filter(player -> radius > player.getLocation().distance(standPoint))
                        .get();
                players.add(nearest);
            }

            if (getArgument("distance").length() != 0) {
                int distance = Integer.parseInt(getArgument("distance"));
                Player nearest = Bukkit.getOnlinePlayers().stream()
                        .filter(player -> player.getLocation().getWorld().getName().equals(standPoint.getWorld().getName()))
                        .min(Comparator.comparing(player -> player.getLocation().distance(standPoint)))
                        .filter(player -> distance > player.getLocation().distance(standPoint))
                        .get();
                players.add(nearest);
            } else {
                Bukkit.getOnlinePlayers().stream()
                        .filter(player -> player.getLocation().getWorld().getName().equals(standPoint.getWorld().getName()))
                        .min(Comparator.comparing(player -> player.getLocation().distance(standPoint)))
                        .ifPresent(players::add);
            }
        } else if (selector.startsWith("@a")) {
            //everyone
            Location standPoint = getLocation(commandSender);

            if(getArgument("region").length() != 0) {
                WGManager manager = WGManager.getInstance();
                String targetRegion = getArgument("region");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    manager.getApplicableRegionSet(player.getLocation(standPoint)).forEach(region -> {
                        if (region.getId().equalsIgnoreCase(targetRegion))
                            players.add(player);
                    });
                }
            } else if (getArgument("r").length() != 0) {
                int radius = Integer.parseInt(getArgument("r"));
                players.addAll(Bukkit.getOnlinePlayers().stream()
                        .filter(player -> player.getLocation().getWorld().getName().equals(standPoint.getWorld().getName()))
                        .filter(player -> radius > player.getLocation().distance(standPoint))
                        .collect(Collectors.toList()));
            } else if (getArgument("distance").length() != 0) {
                int distance = Integer.parseInt(getArgument("distance"));
                players.addAll(Bukkit.getOnlinePlayers().stream()
                        .filter(player -> player.getLocation().getWorld().getName().equals(standPoint.getWorld().getName()))
                        .filter(player -> distance > player.getLocation().distance(standPoint))
                        .collect(Collectors.toList()));
            }

            else {
                players.addAll(Bukkit.getOnlinePlayers().stream()
                        .filter(player -> player.getLocation().getWorld().getName().equals(standPoint.getWorld().getName()))
                        .collect(Collectors.toList()));
            }
        } else if (selector.length() <= 16) {
            //player
            Player player = Bukkit.getPlayer(selector);
            if (player != null)
                players.add(player);
        }

        return players;
    }

    /**
     * attempt to parse the location
     *
     * @param commandSender the sender
     * @return the location or null
     */
    private Location getLocation(CommandSender commandSender) {
        Location initialLocation = new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
        if (commandSender instanceof Player) {
            initialLocation = ((Player) commandSender).getLocation();
        } else if (commandSender instanceof BlockCommandSender) {
            initialLocation = ((BlockCommandSender) commandSender).getBlock().getLocation();
        }

        if (!getArgument("x").equals("") && !getArgument("y").equals("") && !getArgument("z").equals("")) {
            try {
                int x = Integer.parseInt(getArgument("x"));
                int y = Integer.parseInt(getArgument("y"));
                int z = Integer.parseInt(getArgument("z"));
                return new Location(initialLocation.getWorld(), x, y, z);
            } catch (Exception e) {
                return initialLocation;
            }
        }

        return initialLocation;
    }

    private String getArgument(String key) {
        StringBuilder result = new StringBuilder();
        String[] arguments = selector.split(key + "=");
        if (arguments.length == 1) return "";
        for (byte type : arguments[1].getBytes()) {
            char element = (char) type;
            if (element == ',' || element == ']') {
                return result.toString();
            } else {
                result.append(element);
            }
        }
        return result.toString();
    }

}
