package nl.iobyte.commandapi.interfaces;

import nl.iobyte.commandapi.objects.SubCommand;

public interface ICommandMiddleware {

    boolean continueCommand(ICommandExecutor sender, SubCommand command);

}
