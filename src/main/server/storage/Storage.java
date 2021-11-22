package main.server.storage;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    public static ConcurrentHashMap<String, String> stringHashMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, LinkedList<String>> listHashMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, HashMap<String, String>> hashMap = new ConcurrentHashMap<>();
}
