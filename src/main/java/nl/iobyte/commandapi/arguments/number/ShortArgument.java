package nl.iobyte.commandapi.arguments.number;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class ShortArgument implements ICommandArgument<Short> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid short: "+ ChatColor.WHITE+args[0];
    }

    /**
     * Check if argument is valid Short
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        try {
            Short.parseShort(args[0]);
            return new Pair<>(true, 1);
        } catch (Exception e) {
            return new Pair<>(false, 0);
        }
    }

    /**
     * Get Short passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Short
     */
    public Short getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        try {
            return Short.parseShort(args[0]);
        } catch (Exception e) {
            return 0;
        }
    }

}
