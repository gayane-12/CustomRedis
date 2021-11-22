package main.server.utils;

import java.util.Arrays;

public class CommandUtils {
    public static String[] removeFirstElementOfStringArray(String[] array){
        return Arrays.copyOfRange(array, 1, array.length);
    }

}
