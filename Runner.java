public class Runner {
    public static void main(String[] args) {
        int commandParameterIndex = 0;
        Secret secret = BeforeRun.secretInit(args);
        String command = args[commandParameterIndex];

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
