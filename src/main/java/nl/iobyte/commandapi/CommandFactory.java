package nl.iobyte.commandapi;

import javafx.util.Pair;
import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.interfaces.ICommandMiddleware;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import nl.iobyte.commandapi.objects.CommandMap;
import nl.iobyte.commandapi.objects.CommandSyntax;
import nl.iobyte.commandapi.interfaces.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class CommandFactory implements CommandExecutor {

    private final String name;
    private final List<ICommandMiddleware> middlewares = new ArrayList<>();
    private final CommandMap commandMap = new CommandMap();
    private Plugin plugin;

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
     * Get SubCommand's available to CommandSender
     * @param sender CommandSender
     * @return List<SubCommand>
     */
    public List<SubCommand> getApplicableSubCommands(CommandSender sender) {
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
     * @param sender CommandSender
     * @param cmd Command
     * @param s String
     * @param args []String
     * @return Boolean
     */
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
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
            Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                if (sender instanceof Player) {
                    subCommand.onPlayerCommand((Player) sender, finalParsedArguments, i);
                } else {
                    subCommand.onConsoleCommand(sender, finalParsedArguments, i);
                }
            });

            return true;
        }

        if(str != null) {
            sender.sendMessage(ChatColor.RED +str);
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Usage: " + ChatColor.WHITE + syntaxList.get(0).getUsage());
        return true;
    }

    /**
     * Register command
     * @param plugin JavaPlugin
     */
    public void registerCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.getCommand(name).setExecutor(this);
    }

}
