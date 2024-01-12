//This clas is for Cezar algorithm.

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Cezar {

    private final String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private final Map<String, Integer> lOrder = getStringIntegerMap();

    private Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> lOrder = new HashMap<>();
        lOrder.put("a", 0);
        lOrder.put("b", 1);
        lOrder.put("c", 2);
        lOrder.put("d", 3);
        lOrder.put("e", 4);
        lOrder.put("f", 5);
        lOrder.put("g", 6);
        lOrder.put("h", 7);
        lOrder.put("i", 8);
        lOrder.put("j", 9);
        lOrder.put("k", 10);
        lOrder.put("l", 11);
        lOrder.put("m", 12);
        lOrder.put("n", 13);
        lOrder.put("o", 14);
        lOrder.put("p", 15);
        lOrder.put("q", 16);
        lOrder.put("r", 17);
        lOrder.put("s", 18);
        lOrder.put("t", 19);
        lOrder.put("u", 20);
        lOrder.put("v", 21);
        lOrder.put("w", 22);
        lOrder.put("x", 23);
        lOrder.put("y", 24);
        lOrder.put("z", 25);
        return lOrder;
    }
    //'filepath' is the path to the file with source text.
    //'key' is the size of the shift through the alphabet for every letter in the source text.
    //      If the key is negative, shift moves in the direction opposite to the 'direction' value.
    //      If the key is bigger than alphabet size, shift moves through the alphabet several times until the key position is reached.
    //'direction' is the direction of shifting.
    //
    // all symbols that are not present in the alphabet stay unchanged.
    public String render(Path filePath, int key, Direction direction) {

        String inputString = getInputString(filePath);
        Pattern perSymbol = Pattern.compile("");
        String[] inputArray = perSymbol.split(inputString);

        if (key > 26) {
            key = key % 26;
            System.out.println("Actual key: "+key);
        }
        if (key < 0) {
            key = 26 + key;
            System.out.println("Actual key: "+key);
        }


        switch (direction) {
            case FORWARD:
                return moveForward(inputArray, key);
            case BACKWARD:
                return moveBackward(inputArray, key);
            default:
            { System.out.println("Something went wrong");
                return null;}
        }
    }

    private String moveForward(String[] inputArray, Integer key) {
        String res = "";
        for (int i = 0; i < inputArray.length; i++) {

            if (lOrder.containsKey(inputArray[i])) {
                Integer searchedIndex = lOrder.get(inputArray[i]) + key;
                if (searchedIndex > 25) {
                    searchedIndex = searchedIndex % 26;
                }
                res = res + alphabet[searchedIndex];
            } else res = res + inputArray[i];
        }
        return res;
    }

    private String moveBackward(String[] inputArray, Integer key) {
        String res = "";
        for (int i = 0; i < inputArray.length; i++) {

            if (lOrder.containsKey(inputArray[i])) {
                Integer searchedIndex = lOrder.get(inputArray[i]) - key;
                if (searchedIndex < 0) {
                    searchedIndex = 26 + searchedIndex;
                }
                res = res + alphabet[searchedIndex];
            } else res = res + inputArray[i];
        }
        return res;
    }

    public String getInputString(Path filePath) {
        String res = "";
        try {
            res = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return res;
    }
}
