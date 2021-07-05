package nl.iobyte.commandapi.arguments.array;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.List;

public class StringArrayArgument implements ICommandArgument<String[]> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid string array: "+ "§f"+args[0];
    }

    @Override
    public ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        if(!args[0].startsWith("[\"") || !args[0].endsWith("\"]"))
            return new ArgumentCheck(false, 0);

        return new ArgumentCheck(true, 1);
    }

    @Override
    public String[] getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        return args[0].replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
    }

}
