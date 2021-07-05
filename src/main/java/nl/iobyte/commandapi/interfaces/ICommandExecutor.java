package nl.iobyte.commandapi.interfaces;

public interface ICommandExecutor {

    boolean isPlayer();

    boolean hasPermission(String str);

    void sendMessage(String str);

    Object getOriginal();

}
