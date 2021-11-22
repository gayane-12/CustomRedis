package main.server.commands;

import main.server.exceptions.BadRequestException;
import main.server.storage.StorageService;
import main.server.utils.CommandUtils;


public enum CommandStrategy {

    SET {
        //       SET 1 Gayane
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length != 2) throw new BadRequestException("Wrong SET command.");
            return StorageService.putInStringHashMap(keyValues[0], keyValues[1]);
        }
    },
    SETMANY {
        //        SETMANY 1:Gayane 2:Vagr...
        @Override
        public String execute(String[] keyValues) {
            return StorageService.putManyInStringHashMap(keyValues);
        }
    },
    GET {
        //        GET  1
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length != 1) throw new BadRequestException("Wrong GET command.");
            return StorageService.getFromStringHashMap(keyValues[0]);
        }
    },
    GETMANY {
        //        GETMANY 1 2
        @Override
        public String execute(String[] keyValues) {
            return StorageService.getManyFromStringHashMap(CommandUtils.removeFirstElementOfStringArray(keyValues));
        }
    },
    LPUSH {
        //        LPUSH mylist a b ..
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length < 2) throw new BadRequestException("Wrong LPUSH command.");
            return StorageService.leftPushInListHashMap(keyValues[0], CommandUtils.removeFirstElementOfStringArray(keyValues));
        }
    },
    RPUSH {
        //        RPUSH mylist a b ..
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length < 2) throw new BadRequestException("Wrong RPUSH command.");
            return StorageService.rightPushInListHashMap(keyValues[0], CommandUtils.removeFirstElementOfStringArray(keyValues));
        }
    },
    LRANGE {
        @Override
        //        LRANGE mylist 0 2
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length != 3) throw new BadRequestException("Wrong LRANGE command.");
            return StorageService.getFromRangeInListHashMap(keyValues[0], keyValues[1], keyValues[2]);
        }
    },
    HSET {
        //        HSET myhash 1 gayane
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length < 3) throw new BadRequestException("Wrong HSET command.");
            return StorageService.putInHashMap(keyValues[0], CommandUtils.removeFirstElementOfStringArray(keyValues));
        }
    },
    HSETMANY {
        @Override
        //        HSETMANY myhash 1:gayane 2:vagr
        public String execute(String[] keyValues) {
            return StorageService.putManyInHashMap(keyValues[0], CommandUtils.removeFirstElementOfStringArray(keyValues));
        }
    },
    HGET {
        //        HGET myhash 1
        @Override
        public String execute(String[] keyValues) throws BadRequestException {
            if (keyValues.length != 2) throw new BadRequestException("Wrong HGET command.");
            return StorageService.getFromHashMap(keyValues[0], keyValues[1]);
        }
    },
    HGETMANY {
        @Override
        //        HGETMANY myhash 1 2
        public String execute(String[] keyValues) {
            return StorageService.getManyFromHashMap(keyValues[0], CommandUtils.removeFirstElementOfStringArray(keyValues));
        }
    },
    HDEL {
        @Override
        //        HDEL myhash 1
        public String execute(String[] keyValues) {
            return StorageService.deleteFromHashMap(keyValues[0], keyValues[1]);
        }
    };

    public abstract String execute(String[] keyValues) throws BadRequestException;
}

