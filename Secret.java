import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

//This class represents the object that will be encoded/decoded
public class Secret {
    //This is the file path of the file with the source text
    private Path sourceFilePath;
    //This is the file path of the file where encoding result will be stored
    private Path encodedFilePath;
    //This is the file path of the file where decoding result will be stored
    private Path decodedFilePath;
    //This is the shift size for the encoding/decoding
    private Integer key;

    public Path getSourceFilePath() {
        return this.sourceFilePath;
    }

    public Path getEncodedFilePathFilePath() {
        return this.encodedFilePath;
    }

    public Path getDecodedFilePathFilePath() {
        return this.decodedFilePath;
    }

    public Integer getKey() {
        return this.key;
    }

    //If path for the result file is not provided, the result will be stored to the same directory. File name will be the current date time.
    public Secret(String sourceFilePath, Integer key) {
        this.key = key;
        System.out.println("Key:" + key);
        this.sourceFilePath = Paths.get(sourceFilePath);
        System.out.println("Source file path: " + this.sourceFilePath.toString());
        this.encodedFilePath = Paths.get(this.sourceFilePath.getParent().normalize().toString(), "ENCODED" + LocalDateTime.now().toString());
        System.out.println("Encoded file path: " + this.encodedFilePath.toString());
        this.decodedFilePath = Paths.get(this.sourceFilePath.getParent().normalize().toString(), "DECODED" + LocalDateTime.now().toString());
        System.out.println("Decoded file path: " + this.decodedFilePath.toString());
    }

    public Secret(String sourceFilePath) {
        this.key = null;
        System.out.println("Key: UNKNOWN");
        this.sourceFilePath = Paths.get(sourceFilePath);
        System.out.println("Source file path: " + this.sourceFilePath.toString());
        this.encodedFilePath = null;
        this.decodedFilePath = Paths.get(this.sourceFilePath.getParent().normalize().toString(), "DECODED" + LocalDateTime.now().toString());
        System.out.println("Decoded file path: " + this.decodedFilePath.toString());
    }

    public Secret(String sourceFilePath, Integer key, String encodedFilePath, String decodedFilePath) {
        this.key = key;
        this.sourceFilePath = Paths.get(sourceFilePath);
        this.encodedFilePath = Paths.get(encodedFilePath);
        this.decodedFilePath = Paths.get(decodedFilePath);
    }

    //This method encodes the text from the source file and stores the result in the file at 'encodedFilePath' path
    public void encode() {
        Cezar cypher = new Cezar();
        String res = cypher.render(sourceFilePath, key, Direction.FORWARD);
        storeResult(res, encodedFilePath);
        System.out.println("Encoded secret: " + res);
    }

    //This method decodes the text from the source file and stores the result in the file at 'decodedFilePath' path
    public void decode() {
        Cezar cypher = new Cezar();
        String res = cypher.render(sourceFilePath, key, Direction.BACKWARD);
        storeResult(res, decodedFilePath);
        System.out.println("Secret revealed: " + res);
    }

    //This method decodes the text from the ENCODED file and stores the result in the file at 'decodedFilePath' path
    public void testEncoding() {
        Cezar cypher = new Cezar();
        String res = cypher.render(encodedFilePath, key, Direction.BACKWARD);
        storeResult(res, decodedFilePath);
        System.out.println("Secret revealed: " + res);
    }

    //This method runs decoding of the source file content for every possible key. All results are stored in the 'decodedFilePath'.
    // Evaluation of which result makes sense is up to user.
    public void bruteForce() {
        Cezar cypher = new Cezar();
        for (int i = 0; i < cypher.getAlphabetLength(); i++) {
            String res = cypher.render(sourceFilePath, i, Direction.BACKWARD);
            res = System.lineSeparator() + "Key = " + i + ":" + System.lineSeparator() + res + System.lineSeparator();
            storeResult(res, decodedFilePath);
        }
    }

    private void storeResult(String res, Path filePath) {
        try {
            Files.writeString(filePath, res, CREATE, APPEND);
        } catch (IOException e) {
            System.out.println("Can not store result into file: " + e.getMessage());;
        }
    }
}
