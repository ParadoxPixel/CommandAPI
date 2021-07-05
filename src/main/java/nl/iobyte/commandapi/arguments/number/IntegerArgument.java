package nl.iobyte.commandapi.arguments.number;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.List;

public class IntegerArgument implements ICommandArgument<Integer> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid integer: "+ "§f"+args[0];
    }

    /**
     * Check if argument is valid Integer
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        try {
            Integer.parseInt(args[0]);
            return new ArgumentCheck(true, 1);
        } catch (Exception e) {
            return new ArgumentCheck(false, 0);
        }
    }

    /**
     * Get Integer passed by command
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Integer
     */
    public Integer getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception e) {
            return 0;
        }
    }

}
