package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import org.bukkit.command.CommandSender;
import java.util.List;

public class MessageArgument implements ICommandArgument<String> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "";
    }

    /**
     * Check if argument is valid String
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        if(!args[0].startsWith("\""))
            return new ArgumentCheck(true, 1);

        for(int i = 1; i < args.length; i++)
            if(args[i].endsWith("\""))
                return new ArgumentCheck(true, i + 1);

        return new ArgumentCheck(true, args.length);
    }

    /**
     * Get String passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return String
     */
    public String getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return String.join(" ", args).replace("\"", "");
    }

}
