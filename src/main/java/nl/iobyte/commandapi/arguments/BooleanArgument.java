package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class BooleanArgument implements ICommandArgument<Boolean> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid boolean "+ ChatColor.WHITE+args[0];
    }

    /**
     * Check if argument is valid Boolean
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        switch (args[0].toLowerCase()) {
            case "true":
            case "false":
                return new ArgumentCheck(true, 1);
            default:
                return new ArgumentCheck(false, 0);
        }
    }

    /**
     * Get Boolean passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public Boolean getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return "true".equals(args[0].toLowerCase());
    }

}