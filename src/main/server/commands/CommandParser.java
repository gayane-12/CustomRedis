package main.server.commands;


public class CommandParser {
    private String command;

    public CommandParser(String command) {
        this.command = command;
    }

    public String parse() {
        String[] splitCommand = command.split(" ");
        Command command = new Command(CommandStrategy.valueOf(splitCommand[0].toUpperCase()));
        return command.perform(splitCommand);
    }
}
