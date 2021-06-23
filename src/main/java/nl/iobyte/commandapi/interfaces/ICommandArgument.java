package nl.iobyte.commandapi.interfaces;

import nl.iobyte.commandapi.objects.ArgumentCheck;
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
     * @return ArgumentCheck
     */
    ArgumentCheck checkArgument(CommandSender sender, String[] args, List<Object> previousArguments);

    /**
     * Get object of argument
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Object of type T
     */
    T getArgument(CommandSender sender, String[] args, List<Object> previousArguments);

}
