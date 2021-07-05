package nl.iobyte.commandapi.interfaces;

import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.List;

public interface ICommandArgument<T> {

    /**
     * Message to display when giving an error
     * @return String
     */
    String getMessage(String[] args);

    /**
     * Check if valid argument
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return ArgumentCheck
     */
    ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments);

    /**
     * Get object of argument
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Object of type T
     */
    T getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments);

}
