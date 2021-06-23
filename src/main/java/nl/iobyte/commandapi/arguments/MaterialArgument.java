package nl.iobyte.commandapi.arguments;

import nl.iobyte.commandapi.interfaces.ICommandArgument;
import nl.iobyte.commandapi.objects.ArgumentCheck;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import java.util.Arrays;
import java.util.List;

public class MaterialArgument implements ICommandArgument<Enum<?>> {

    private final Material[] validArgumentHolders;

    public MaterialArgument(Material... validArgumentHolders) {
        this.validArgumentHolders = validArgumentHolders;
    }

    /**
     * Message to display when giving an error
     * @return String
     */
    public String getMessage(String[] args) {
        return "No material with name: "+args[0];
    }

    /**
     * Check if argument is valid Material
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Boolean
     */
    public ArgumentCheck checkArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        Material material = Material.getMaterial(args[0]);
        if(material == null)
            return new ArgumentCheck(false, 0);

        if(validArgumentHolders == null || validArgumentHolders.length == 0)
            return new ArgumentCheck(true, 1);

        return new ArgumentCheck(Arrays.stream(validArgumentHolders).anyMatch(m -> m == material), 1);
    }

    /**
     * Get Material passed by command
     * @param sender CommandSender
     * @param args Arguments passed by Command
     * @param previousArguments Previous arguments
     * @return Material
     */
    public Material getArgument(CommandSender sender, String[] args, List<Object> previousArguments) {
        return Material.getMaterial(args[0]);
    }

}
