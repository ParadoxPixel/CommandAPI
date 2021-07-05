package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.List;

public class BooleanArgument implements ICommandArgument<Boolean> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid boolean "+ "§f" + args[0];
    }

    /**
     * Check if argument is valid Boolean
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
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
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public Boolean getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        return "true".equals(args[0].toLowerCase());
    }

}