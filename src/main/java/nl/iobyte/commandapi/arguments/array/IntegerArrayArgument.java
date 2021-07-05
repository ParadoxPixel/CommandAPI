package nl.iobyte.commandapi.arguments.array;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.List;

public class IntegerArrayArgument implements ICommandArgument<Integer[]> {

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid integer array: "+ "§f"+args[0];
    }

    @Override
    public ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        String[] items = args[0].replace("[", "").replace("]", "").split(",");
        try {
            for(String item : items)
                Integer.parseInt(item);

            return new ArgumentCheck(true, 1);
        } catch (Exception e) {
            return new ArgumentCheck(false, 0);
        }
    }

    @Override
    public Integer[] getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        String[] items = args[0].replace("[", "").replace("]", "").split(",");
        Integer[] results = new Integer[items.length];
        for(int i = 0; i < items.length; i++)
            results[i] = Integer.parseInt(items[i]);

        return results;
    }

}
