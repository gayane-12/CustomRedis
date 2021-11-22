package main.server.commands;

import main.server.exceptions.BadRequestException;

public class Command {
    private CommandStrategy commandStrategy;

    public Command(CommandStrategy commandStrategy) {
        this.commandStrategy = commandStrategy;
    }

    public void setCommandStrategy(CommandStrategy commandStrategy) {
        this.commandStrategy = commandStrategy;
    }

    public String perform(String[] commands) throws BadRequestException {
        return commandStrategy.execute(commands);
    }

}
