package nl.iobyte.commandapi.objects;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import java.util.ArrayList;
import java.util.List;

public class CommandSyntax {

    private final String usage;
    private final List<ICommandArgument<?>> arguments = new ArrayList<>();
    private boolean allowConsole = true;

    public CommandSyntax(String usage) {
        this.usage = usage;
    }

    /**
     * Get usage of syntax
     * @return String
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Get list of arguments
     * @return List<ICommandArgument<?>>
     */
    public List<ICommandArgument<?>> getArguments() {
        return arguments;
    }

    public CommandSyntax addArgument(ICommandArgument<?> argument) {
        arguments.add(argument);
        return this;
    }

    /**
     * Check if console is allowed
     * @return Boolean
     */
    public boolean isConsoleAllowed() {
        return allowConsole;
    }

    /**
     * Set if console is allowed
     * @param allowConsole Boolean
     */
    public CommandSyntax setAllowConsole(boolean allowConsole) {
        this.allowConsole = allowConsole;
        return this;
    }

}
