public class Runner {
    public static void main(String[] args) {

        Secret secret;

        try {
            String command = args[0];
            String filePath = args[1];
            if (args.length > 2) {
                Integer key = Integer.parseInt(args[2]);
                secret = new Secret(filePath, key);
            } else secret = new Secret(filePath);

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
                default:
                    System.out.println("Input parameter " + command + "is unknown.");
            }
        } catch (java.lang.IndexOutOfBoundsException e) {
            System.out.println("\nSome arguments are missing. Please, follow the instruction:\n\n" +
                    "Run the program with the command 'java -jar myApp.jar command filePath key', where\n\n" +
                    "'command' is one of 'ENCRYPT', 'DECRYPT', 'BRUTE_FORCE;\n" +
                    "'filePath' is the absolute path to the file with the source code;\n" +
                    "'key' is the cypher key. In case of BRUTE_FORCE command the key is omitted.");
        }
    }


}
