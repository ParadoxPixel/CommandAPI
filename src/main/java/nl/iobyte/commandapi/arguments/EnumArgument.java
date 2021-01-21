package nl.iobyte.commandapi.arguments;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;
import java.util.List;

public class EnumArgument implements ICommandArgument<Enum<?>> {

    private final Enum<?>[] validArgumentHolders;

    public EnumArgument(Enum<?>... validArgumentHolders) {
        this.validArgumentHolders = validArgumentHolders;
    }

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "Invalid value "+ChatColor.WHITE+args[0]+ChatColor.RED+" for "+validArgumentHolders[0].name();
    }

    /**
     * Check if argument is valid Enum
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public Pair<Boolean, Integer> checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return new Pair<>(Arrays.stream(validArgumentHolders).map(Enum::toString).anyMatch(args[0]::equalsIgnoreCase), 1);
    }

    /**
     * Get Enum passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Enum
     */
    public Enum<?> getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.toString().equalsIgnoreCase(args[0])).findFirst().orElse(null);
    }

}
