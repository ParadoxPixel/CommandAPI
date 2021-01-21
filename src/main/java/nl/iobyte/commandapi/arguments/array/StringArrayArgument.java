package nl.iobyte.commandapi.arguments.array;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class StringArrayArgument implements ICommandArgument<String[]> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid string array: "+ ChatColor.WHITE+args[0];
    }

    @Override
    public Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        if(!args[0].startsWith("[\"") || !args[0].endsWith("\"]"))
            return new Pair<>(false, 0);

        return new Pair<>(true, 1);
    }

    @Override
    public String[] getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return args[0].replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
    }

}
