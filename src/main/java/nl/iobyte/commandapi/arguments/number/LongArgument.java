package nl.iobyte.commandapi.arguments.number;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.List;

public class LongArgument implements ICommandArgument<Long> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid long: "+ "§f"+args[0];
    }

    /**
     * Check if argument is valid Long
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        try {
            Long.parseLong(args[0]);
            return new ArgumentCheck(true, 1);
        } catch (Exception e) {
            return new ArgumentCheck(false, 0);
        }
    }

    /**
     * Get Long passed by command
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Long
     */
    public Long getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        try {
            return Long.parseLong(args[0]);
        } catch (Exception e) {
            return 0L;
        }
    }

}
