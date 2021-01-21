package nl.iobyte.commandapi.interfaces;

import org.bukkit.command.CommandSender;

public interface ICommandMiddleware {

    boolean continueCommand(CommandSender sender, SubCommand command);

}
