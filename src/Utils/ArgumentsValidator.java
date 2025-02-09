package Utils;

public class ArgumentsValidator implements IArgumentsValidator {
    public ValidatedArguments validate(String[] args) {
        if (args == null || args.length < 2) {
            printUsage();
            System.exit(1);
        }

        String command = args[0];
        String inputFile = args[1];

        if (!command.equalsIgnoreCase("parse")) {
            System.out.println("Неверная команда: " + command);
            printUsage();
            System.exit(1);
        }

        return new ValidatedArguments(command, inputFile);
    }

    private void printUsage() {
        System.out.println("Использование: CsvParser parse <inputFile>");
    }
}
