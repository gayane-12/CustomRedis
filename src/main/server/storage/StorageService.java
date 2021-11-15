package main.server.storage;



public class StorageService {

//    TODO think about many key values
    public static String putInStringHashMap(String key, String value) {
        Storage.stringHashMap.put(key, value);
        return "OK";
    }

    public static String getFromStringHashMap(String key) {
        return Storage.stringHashMap.get(key);
    }
}
