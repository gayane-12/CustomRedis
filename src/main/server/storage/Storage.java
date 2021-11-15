package main.server.storage;


import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
//    TODO think about constructor version
    public static ConcurrentHashMap<String, String> stringHashMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, LinkedList<String>> listHashMap = new ConcurrentHashMap<>();
}
