package main.server.commands;

public class Command {
    private CommandStrategy commandStrategy;

    public Command(CommandStrategy commandStrategy) {
        this.commandStrategy = commandStrategy;
    }

    public void setCommandStrategy(CommandStrategy commandStrategy) {
        this.commandStrategy = commandStrategy;
    }

    public String perform(String[] commands) {
        return commandStrategy.execute(commands);
    }

}
