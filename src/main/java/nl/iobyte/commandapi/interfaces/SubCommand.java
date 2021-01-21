package nl.iobyte.commandapi.interfaces;

import nl.iobyte.commandapi.objects.CommandSyntax;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class SubCommand {

    private final String[] name;
    private final String permission;
    private final List<CommandSyntax> syntaxList = new ArrayList<>();

    public SubCommand(String[] name) {
        this.name = name;
        this.permission = "";
    }

    public SubCommand(String permission, String... name) {
        this.name = name;
        this.permission = permission;
    }

    /**
     * Get name of sub-command
     * @return String
     */
    public String[] getName() {
        return name;
    }

    /**
     * Get permission of sub-command
     * @return String
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Check if sub-command has permission
     * @return Boolean
     */
    public boolean hasPermission() {
        return permission != null && !permission.isEmpty();
    }

    /**
     * Get list with syntax's
     * @return List<CommandSyntax>
     */
    public List<CommandSyntax> getSyntaxList() {
        return syntaxList;
    }

    /**
     * Get all Syntax's sender can use
     * @param sender CommandSender
     * @return List<CommandSyntax>
     */
    public List<CommandSyntax> getApplicableSyntaxList(CommandSender sender) {
        List<CommandSyntax> list = new ArrayList<>();
        for(CommandSyntax syntax : syntaxList) {
            if(!syntax.isConsoleAllowed() && !(sender instanceof Player))
                continue;

            list.add(syntax);
        }

        return list;
    }

    /**
     * Add list of syntax's to sub-command
     * @param syntaxList List<CommandSyntax>
     */
    public void addSyntaxList(List<CommandSyntax> syntaxList) {
        this.syntaxList.addAll(syntaxList);
    }

    /**
     * Add syntax to sub-command
     * @param usage String
     * @return CommandSyntax
     */
    public CommandSyntax addSyntax(String usage) {
        CommandSyntax syntax = new CommandSyntax(usage);
        syntaxList.add(syntax);
        return syntax;
    }

    /**
     * When player executes command
     * @param player Player
     * @param args Object[]
     * @param syntaxUsed Integer
     */
    public abstract void onPlayerCommand(Player player, List<Object> args, int syntaxUsed);

    /**
     * When console executes command
     * @param sender CommandSender
     * @param args Object[]
     * @param syntaxUsed Integer
     */
    public abstract void onConsoleCommand(CommandSender sender, List<Object> args, int syntaxUsed);

}
