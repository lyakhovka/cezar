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

    private final Map<String, Integer> lettersOrder = getStringIntegerMap();

    private Map<String, Integer> getStringIntegerMap() {
        Map<String, Integer> lettersOrder = new HashMap<>();
        lettersOrder.put("a", 0);
        lettersOrder.put("b", 1);
        lettersOrder.put("c", 2);
        lettersOrder.put("d", 3);
        lettersOrder.put("e", 4);
        lettersOrder.put("f", 5);
        lettersOrder.put("g", 6);
        lettersOrder.put("h", 7);
        lettersOrder.put("i", 8);
        lettersOrder.put("j", 9);
        lettersOrder.put("k", 10);
        lettersOrder.put("l", 11);
        lettersOrder.put("m", 12);
        lettersOrder.put("n", 13);
        lettersOrder.put("o", 14);
        lettersOrder.put("p", 15);
        lettersOrder.put("q", 16);
        lettersOrder.put("r", 17);
        lettersOrder.put("s", 18);
        lettersOrder.put("t", 19);
        lettersOrder.put("u", 20);
        lettersOrder.put("v", 21);
        lettersOrder.put("w", 22);
        lettersOrder.put("x", 23);
        lettersOrder.put("y", 24);
        lettersOrder.put("z", 25);
        return lettersOrder;
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

        if (key > alphabet.length) {
            key = key % alphabet.length;
            System.out.println("Actual key: "+key);
        }
        if (key < 0) {
            key = alphabet.length + key;
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

            if (lettersOrder.containsKey(inputArray[i])) {
                Integer searchedIndex = lettersOrder.get(inputArray[i]) + key;
                if (searchedIndex > alphabet.length-1) {
                    searchedIndex = searchedIndex % alphabet.length;
                }
                res = res + alphabet[searchedIndex];
            } else res = res + inputArray[i];
        }
        return res;
    }

    private String moveBackward(String[] inputArray, Integer key) {
        String res = "";
        for (int i = 0; i < inputArray.length; i++) {

            if (lettersOrder.containsKey(inputArray[i])) {
                Integer searchedIndex = lettersOrder.get(inputArray[i]) - key;
                if (searchedIndex < 0) {
                    searchedIndex = alphabet.length + searchedIndex;
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
