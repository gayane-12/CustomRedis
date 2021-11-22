package main.server.commands;

import main.server.exceptions.BadRequestException;
import main.server.utils.CommandUtils;

public class CommandParser {
    private String command;

    public CommandParser(String command) {
        this.command = command;
    }

    public String parse() throws BadRequestException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length < 2) throw new BadRequestException("Wrong command.");
        try {
            CommandStrategy commandStrategy = CommandStrategy.valueOf(splitCommand[0].toUpperCase());
            Command command = new Command(commandStrategy);
            String[] keyValues = CommandUtils.removeFirstElementOfStringArray(splitCommand);
            return command.perform(keyValues);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Command " + splitCommand[0].toUpperCase() + " doesn't exist.");
        }
    }
}
