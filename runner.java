import java.sql.SQLOutput;

public class runner {
    public static void main(String[] args) {

        Secret secret = new Secret("secret", 15);
//        System.out.println("Secret is encoded: " + secret.encode());
        System.out.println("Secret is revealed: " + secret.decode());

    }


}
