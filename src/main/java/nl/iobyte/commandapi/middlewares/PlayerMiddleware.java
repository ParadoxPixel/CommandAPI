package nl.iobyte.commandapi.middlewares;

import nl.iobyte.commandapi.interfaces.ICommandMiddleware;
import nl.iobyte.commandapi.interfaces.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerMiddleware implements ICommandMiddleware {

    @Override
    public boolean continueCommand(CommandSender sender, SubCommand command) {
        return sender instanceof Player;
    }

}
