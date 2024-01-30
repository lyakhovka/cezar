import java.nio.file.Files;
import java.nio.file.Path;

public class BeforeRun {

    private static final String ERROR_LOG = """
                        
            Some arguments are missing or not valid. Please, follow the instruction:
                        
            Run the program with the command 'java -jar myApp.jar command filePath key', where
            'command' is one of 'ENCRYPT', 'DECRYPT', 'BRUTE_FORCE';
            'filePath' is the absolute path to the file with the source code;
            'key' is the cypher key. In case of BRUTE_FORCE command the key is omitted.""";

//Validation for input parameters:
//If amount of input params are less then 2
//OR any of input params is not valid
//OR amount of input params are 2 but command is not 'BRUTE_FORCE'
//then info message is shown and program ends with code 1.
    private static void validateParams(String[] args) {
        int argNumber = args.length;
        String command = "";
        String filePath = "";
        String key = "";

        if (argNumber < 2) {
            showInfoAndQuit();
        } else {
            command = args[0];
            filePath = args[1];
        }
        if (argNumber > 2) key = args[2];

        if ((argNumber < 2 || !validateCommand(command) || (!validatePath(filePath))) ||
                (argNumber == 2 && !command.equals("BRUTE_FORCE")) ||
                (argNumber > 2 && !validateKey(key))) {
            showInfoAndQuit();
        } else System.out.println("Input Parameters are Valid.");
    }

    public static Secret secretInit(String[] args) {
        validateParams(args);
        return args.length == 2 ? new Secret(args[1]) : new Secret(args[1], Integer.parseInt(args[2]));
    }

    private static void showInfoAndQuit() {
        System.out.println(ERROR_LOG);
        System.exit(1);
    }

    private static boolean validateCommand(String command) {
        for (Command c : Command.values()) {
            if (c.toString().equals(command)) return true;
        }
        return false;
    }

    private static boolean validatePath(String path) {
        return Files.exists(Path.of(path));
    }

    private static boolean validateKey(String key) {
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
