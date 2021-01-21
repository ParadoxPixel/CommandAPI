package nl.iobyte.commandapi.arguments;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;
import java.util.List;

public class StringArgument implements ICommandArgument<String> {

    private final String[] validArguments;

    public StringArgument(String... validArguments) {
        this.validArguments = validArguments;
    }

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid string: "+ ChatColor.WHITE+args[0];
    }

    /**
     * Check if argument is valid String
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        if(validArguments == null || validArguments.length == 0)
            return new Pair<>(true, 1);

        return new Pair<>(Arrays.stream(validArguments).anyMatch(args[0]::equalsIgnoreCase), 1);
    }

    /**
     * Get String passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return String
     */
    public String getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return args[0];
    }

}