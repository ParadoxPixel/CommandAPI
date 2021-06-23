package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class PlayerArgument implements ICommandArgument<Player> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "No player found with name: "+ ChatColor.WHITE+args[0];
    }

    /**
     * Check if argument is valid Player
     * @param sender CommandSender
     * @param args Argument passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return new ArgumentCheck(Bukkit.getPlayer(args[0]) != null, 1);
    }

    /**
     * Get Player passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Player
     */
    public Player getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return Bukkit.getPlayer(args[0]);
    }

}
