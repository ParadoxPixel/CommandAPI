package nl.iobyte.commandapi.arguments.array;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class IntegerArrayArgument implements ICommandArgument<Integer[]> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid integer array: "+ ChatColor.WHITE+args[0];
    }

    @Override
    public Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        String[] items = args[0].replace("[", "").replace("]", "").split(",");
        try {
            for(String item : items)
                Integer.parseInt(item);

            return new Pair<>(true, 1);
        } catch (Exception e) {
            return new Pair<>(false, 0);
        }
    }

    @Override
    public Integer[] getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        String[] items = args[0].replace("[", "").replace("]", "").split(",");
        Integer[] results = new Integer[items.length];
        for(int i = 0; i < items.length; i++)
            results[i] = Integer.parseInt(items[i]);

        return results;
    }

}
