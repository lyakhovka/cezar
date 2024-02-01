public class Runner {
    public static void main(String[] args) {
        int commandParameterIndex = 0;
        int sourceFilePathIndex = 1;
        int keyIndex = 2;

        BeforeRun.validateParams(args);
        String command = args[commandParameterIndex];
        Secret secret = args.length == 2 ? new Secret(args[sourceFilePathIndex]) : new Secret(args[sourceFilePathIndex], Integer.parseInt(args[keyIndex]));


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
