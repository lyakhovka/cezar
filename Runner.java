import java.sql.SQLOutput;

public class runner {
    public static void main(String[] args) {

        String command = args[0];
        String filePath = args[1];
        if(args.length>2) {String key = args[2];}

        Secret secret = new Secret(filePath);
        switch (command){
            case "ENCRYPT":
                secret.encrypt();
                break;
            case "DECRYPT":
                secret.decrypt();
                break;
            case "BRUTE_FORCE":
                secret.bruteForce();
                break;
            default:
                System.out.println("Input parameter " + command + "is unknown.");
        }
    }


}
