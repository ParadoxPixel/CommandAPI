package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
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
        return "Invalid value "+"§f"+args[0]+"§4"+" for "+validArgumentHolders[0].name();
    }

    /**
     * Check if argument is valid Enum
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        return new ArgumentCheck(Arrays.stream(validArgumentHolders).map(Enum::toString).anyMatch(args[0]::equalsIgnoreCase), 1);
    }

    /**
     * Get Enum passed by command
     * @param sender ICommandExecutor
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Enum
     */
    public Enum<?> getArgument(ICommandExecutor sender, String[] args, List<Object> previousArguments) {
        return Arrays.stream(validArgumentHolders).filter(enumVariable -> enumVariable.toString().equalsIgnoreCase(args[0])).findFirst().orElse(null);
    }

}
