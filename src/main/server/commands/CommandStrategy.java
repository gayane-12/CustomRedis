package main.server.commands;

import main.server.storage.StorageService;

public enum CommandStrategy {

    SET {
        @Override
        public String execute(String[] commands) {
            return StorageService.putInStringHashMap(commands[1], commands[2]);
        }
    },

    GET {
        @Override
        public String execute(String[] commands) {
            return StorageService.getFromStringHashMap(commands[1]);
        }
    };

    public abstract String execute(String[] commands);
}

