package nl.iobyte.commandapi;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.interfaces.ICommandMiddleware;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.objects.CommandMap;
import nl.iobyte.commandapi.objects.CommandSyntax;
import nl.iobyte.commandapi.objects.SubCommand;
import nl.iobyte.commandapi.interfaces.ICommandExecutor;
import java.util.*;

public class CommandFactory {

    private final String name;
    private final List<ICommandMiddleware> middlewares = new ArrayList<>();
    private final CommandMap commandMap = new CommandMap();

    public CommandFactory(String name) {
        this.name = name;
    }

    /**
     * Get name of command
     * @return String
     */
    public String getName() {
        return name;
    }

    public CommandFactory addMiddleware(ICommandMiddleware middleware) {
        if(middleware != null)
            middlewares.add(middleware);

        return this;
    }

    /**
     * Add sub-command to CommandFactory
     * @param subCommand SubCommand
     * @return CommandFactory
     */
    public CommandFactory addSubCommand(SubCommand subCommand) {
        if(subCommand == null)
            return this;

        commandMap.addSubCommand(subCommand);
        return this;
    }

    /**
     * Get collection of all sub-commands
     * @return Collection<SubCommand>
     */
    public Collection<SubCommand> getSubCommands() {
        return commandMap.getSubCommands();
    }

    /**
     * Get SubCommand's available to ICommandExecutor
     * @param sender ICommandExecutor
     * @return List<SubCommand>
     */
    public List<SubCommand> getApplicableSubCommands(ICommandExecutor sender) {
        List<SubCommand> list = new ArrayList<>();
        for(SubCommand subCommand : commandMap.getSubCommands()) {
            if (subCommand.hasPermission() && !sender.hasPermission(subCommand.getPermission()))
                continue;

            if(subCommand.getApplicableSyntaxList(sender).size() == 0)
                continue;

            list.add(subCommand);
        }

        return list;
    }

    /**
     * When a command gets fired
     * @param sender ICommandExecutor
     * @param args []String
     * @return Boolean
     */
    public boolean onCommand(ICommandExecutor sender, String[] args) {
        if(args.length == 0)
            args = new String[]{"help"};

        //Get SubCommand if exists
        SubCommand subCommand = commandMap.getSubCommand(args);
        if(subCommand == null || subCommand.getSyntaxList().isEmpty())
            return false;

        //Remove sub-command name from arguments
        args = Arrays.copyOfRange(args, subCommand.getName().length, args.length);

        //Handle Middleware
        for(ICommandMiddleware middleware : middlewares)
            if(!middleware.continueCommand(sender, subCommand))
                return true;

        //Pre assign variables
        boolean b;
        String str = null;
        ArgumentCheck pair;
        List<ICommandArgument<?>> arguments;
        List<Object> parsedArguments;
        List<CommandSyntax> syntaxList = subCommand.getApplicableSyntaxList(sender);

        //Try possible syntax's
        for(CommandSyntax syntax : syntaxList) {
            arguments = syntax.getArguments();
            if(arguments.size() > args.length)
                continue;

            b = true;
            int j = 0;
            parsedArguments = new ArrayList<>();
            for (ICommandArgument<?> argument : arguments) {
                String[] array = Arrays.copyOfRange(args, j, args.length);
                pair = argument.checkArgument(sender, array, parsedArguments);
                if (!pair.getKey()) {
                    str = argument.getMessage(array);
                    b = false;
                    break;
                }

                parsedArguments.add(argument.getArgument(sender, Arrays.copyOfRange(array, 0, pair.getValue()), parsedArguments));
                j += pair.getValue();
            }

            if(j != args.length)
                continue;

            if(!b)
                continue;

            int i = subCommand.getSyntaxList().indexOf(syntax);
            List<Object> finalParsedArguments = parsedArguments;
            subCommand.onCommand(sender, finalParsedArguments, i);
            return true;
        }

        if(str != null) {
            sender.sendMessage("§4" +str);
            return true;
        }

        sender.sendMessage("§4" + "Usage: " + "§f" + syntaxList.get(0).getUsage());
        return true;
    }

}
