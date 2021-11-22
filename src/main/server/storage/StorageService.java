package main.server.storage;

import main.server.exceptions.BadRequestException;

import java.util.HashMap;
import java.util.LinkedList;

public class StorageService {

    public static String putInStringHashMap(String key, String value) {
        Storage.stringHashMap.put(key, value);
        return "OK";
    }

    public static String putManyInStringHashMap(String[] keyValues) {
        for (String keyValue :
                keyValues) {
            String[] splitKeyValue = keyValue.split(":");
            Storage.stringHashMap.put(splitKeyValue[0], splitKeyValue[1]);
        }
        return "OK";
    }

    public static String getFromStringHashMap(String key) {
        return Storage.stringHashMap.get(key);
    }

    public static String getManyFromStringHashMap(String[] keys) {
        StringBuilder values = new StringBuilder();
        for (String key :
                keys) {
            values.append(getFromStringHashMap(key)).append(" ");
        }
        return values.toString();
    }

    public static String leftPushInListHashMap(String key, String[] values) {
        Storage.listHashMap.computeIfAbsent(key, k -> new LinkedList<>());
        for (String value : values) {
            Storage.listHashMap.get(key).addFirst(value);
        }
        return "size: " + Storage.listHashMap.get(key).size();
    }

    public static String rightPushInListHashMap(String key, String[] values) {
        Storage.listHashMap.computeIfAbsent(key, k -> new LinkedList<>());
        for (String value : values) {
            Storage.listHashMap.get(key).add(value);
        }
        return "size: " + Storage.listHashMap.get(key).size();
    }

    public static String getFromRangeInListHashMap(String key, String startIndex, String endIndex) throws BadRequestException {
        int startInd, endInd;
        try {
            startInd = Integer.parseInt(startIndex);
            endInd = Integer.parseInt(endIndex);
        } catch (NumberFormatException error) {
            throw new BadRequestException("Wrong index format.");
        }

        if (startInd > Storage.listHashMap.get(key).size() - 1) return "Empty list";
        if (endInd == -1 || endInd > Storage.listHashMap.get(key).size() - 1)
            endInd = Storage.listHashMap.get(key).size() - 1;

        StringBuilder values = new StringBuilder("\n");
        for (int i = startInd; i <= endInd; i++) {
            values.append(i).append(". ").append(Storage.listHashMap.get(key).get(i)).append("\n");
        }

        return values.toString();
    }

    public static String putInHashMap(String key, String[] keyValue) {
        Storage.hashMap.computeIfAbsent(key, k -> new HashMap<>());
        Storage.hashMap.get(key).put(keyValue[0], keyValue[1]);
        return "size: " + Storage.hashMap.get(key).size();
    }

    public static String putManyInHashMap(String key, String[] keyValues) {
        for (String keyValue :
                keyValues) {
            String[] splitCommand = keyValue.split(":");
            putInHashMap(key, splitCommand);
        }
        return "size: " + Storage.hashMap.get(key).size();
    }

    public static String getFromHashMap(String keyOfHashMap, String keyOfValue) {
        return Storage.hashMap.get(keyOfHashMap).get(keyOfValue);
    }

    public static String getManyFromHashMap(String keyOfHashMap, String[] keysOfValue) {
        StringBuilder values = new StringBuilder();
        for (String key :
                keysOfValue) {
            values.append(getFromHashMap(keyOfHashMap, key)).append(" ");
        }
        return values.toString();
    }

    public static String deleteFromHashMap(String keyOfHashMap, String keyOfValue) {
        if (Storage.hashMap.get(keyOfHashMap) == null) return "0";
        Storage.hashMap.get(keyOfHashMap).remove(keyOfValue);
        return "1";
    }
}
