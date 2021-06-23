package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.objects.SpigotPlayerSelector;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class PlayersArgument implements ICommandArgument<List<Player>> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "No player(s) found for: "+ ChatColor.WHITE+args[0];
    }

    /**
     * Check if argument is valid Player
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        List<Player> players = new SpigotPlayerSelector(args[0]).getPlayers(sender);
        return new ArgumentCheck(players != null && !players.isEmpty(), 1);
    }

    /**
     * Get Player passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Player
     */
    public List<Player> getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return new SpigotPlayerSelector(args[0]).getPlayers(sender);
    }

}
