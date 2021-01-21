package nl.iobyte.commandapi.interfaces;

import javafx.util.Pair;
import org.bukkit.command.CommandSender;
import java.util.List;

public interface ICommandArgument<T> {

    /**
     * Message to display when giving an error
     * @return String
     */
    String getMessage(String[] args);

    /**
     * Check if valid argument
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Pair<Boolean, Integer>
     */
    Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments);

    /**
     * Get object of argument
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Object of type T
     */
    T getArgument(CommandSender sender, String[] args, List<Object> previousArguments);

}
