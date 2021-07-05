package nl.iobyte.commandapi.objects;

import java.util.*;

public class CommandMap {

    private final Map<String, Object> map = new HashMap<>();
    private final List<SubCommand> subCommands = new ArrayList<>();

    /**
     * Get SubCommand from Args
     * @param args String[]
     * @return SubCommand
     */
    public SubCommand getSubCommand(String... args) {
        return getSubCommand(0, args);
    }

    /**
     * Get SubCommand from Args
     * @param i Integer
     * @param args String[]
     * @return SubCommand
     */
    private SubCommand getSubCommand(int i, String... args) {
        if(args.length <= i)
            return getSubCommand(0, "");

        Object obj = map.get(args[i]);
        if(obj == null)
            return (SubCommand) map.get("");

        if(args.length == 1) {
            if(obj instanceof CommandMap)
                return ((CommandMap) obj).getSubCommand(0,"");

            return (SubCommand) obj;
        }

        if(obj instanceof CommandMap)
            return ((CommandMap) obj).getSubCommand(i + 1, args);

        return null;
    }

    /**
     * Get List of all SubCommands
     * @return List<SubCommand>
     */
    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    /**
     * Add SubCommand
     * @param command SubCommand
     */
    public void addSubCommand(SubCommand command) {
        addSubCommand(command.getName(), 0, command);
        for(SubCommand subCommand : subCommands)
            if(Arrays.equals(subCommand.getName(), command.getName()))
                return;

        subCommands.add(command);
    }

    /**
     * Add SubCommand
     * @param name String[]
     * @param i Integer
     * @param command SubCommand
     */
    private void addSubCommand(String[] name, int i, SubCommand command) {
        if(name.length <= i) {
            if(map.containsKey("")) {
                ((SubCommand) map.get("")).addSyntaxList(command.getSyntaxList());
                return;
            }

            map.put("", command);
            return;
        }

        CommandMap cm;
        if(map.containsKey(name[i])) {
            cm = (CommandMap) map.get(name[i]);
        } else {
            cm = new CommandMap();
            map.put(name[i], cm);
        }

        cm.addSubCommand(name, i + 1, command);

    }

}
