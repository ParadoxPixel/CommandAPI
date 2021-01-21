package nl.iobyte.commandapi.arguments.number;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class FloatArgument implements ICommandArgument<Float> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid float: "+ ChatColor.WHITE+args[0];
    }

    /**
     * Check if argument is valid Float
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        try {
            Float.parseFloat(args[0]);
            return new Pair<>(true, 1);
        } catch (Exception e) {
            return new Pair<>(false, 0);
        }
    }

    /**
     * Get Float passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Float
     */
    public Float getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        try {
            return Float.parseFloat(args[0]);
        } catch (Exception e) {
            return 0f;
        }
    }

}