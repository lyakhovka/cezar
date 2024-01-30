public class Runner {
    public static void main(String[] args) {

        Secret secret = BeforeRun.secretInit(args);
        String command = args[0];

        switch (command) {
            case "ENCRYPT":
                secret.encrypt();
                break;
            case "DECRYPT":
                secret.decrypt();
                break;
            case "BRUTE_FORCE":
                secret.bruteForce();
                break;
        }
    }
}
