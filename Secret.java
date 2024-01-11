
//This class represents the object that will be encoded/decoded
public class Secret {
    //This is the file path of the file with the source text
    private String filePath;
    //This is the shift size for the encoding/decoding
    private Integer key;

    public String getFilePath() {
        return this.filePath;
    }

    public Integer getKey() {
        return this.key;
    }

    public Secret(String filePath, Integer key) {
        this.filePath = filePath;
        this.key = key;
    }

    //This method encode the text from the source file
    public String encode() {
        Cezar cypher = new Cezar();
        return cypher.render(filePath, key, Direction.FORWARD);
    }

    //This method decode the text from the source file
    public String decode() {
        Cezar cypher = new Cezar();
        return cypher.render(filePath, key, Direction.BACKWARD);
    }
}
