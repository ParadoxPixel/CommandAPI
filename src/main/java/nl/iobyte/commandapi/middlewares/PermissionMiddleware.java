package nl.iobyte.commandapi.middlewares;

import nl.iobyte.commandapi.interfaces.ICommandMiddleware;
import nl.iobyte.commandapi.interfaces.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class PermissionMiddleware implements ICommandMiddleware {

    private final String[] permissions;

    public PermissionMiddleware() {
        permissions = null;
    }

    public PermissionMiddleware(String... permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean continueCommand(CommandSender sender, SubCommand command) {
        if(command.hasPermission() && !sender.hasPermission(command.getPermission())) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
            return false;
        }

        if(permissions != null && permissions.length != 0) {
            for (String permission : permissions) {
                if (!sender.hasPermission(permission)) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command");
                    return false;
                }
            }
        }

        return true;
    }
}
