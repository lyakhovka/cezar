import java.sql.SQLOutput;

public class runner {
    public static void main(String[] args) {

        Secret secret = new Secret("/home/olena/work/cezar/secret", 15);
        secret.encode();
        secret.testEncoding();

    }


}
