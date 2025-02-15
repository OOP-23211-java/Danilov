package Utils;

import Exceptions.InvalidArgumentsException;

public class ArgumentsValidator extends BaseArgumentsValidator {
    public ValidatedArguments validate(String[] args) throws InvalidArgumentsException {
        checkArgsCount(args.length);

        String command = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        checkCommand(command);
        return new ValidatedArguments(command, inputFile, outputFile);
    }

    @Override
    protected void checkArgsCount(int count) throws InvalidArgumentsException {
        if (count < 3){
            printUsage();
            throw new InvalidArgumentsException("Неверное количество аргументов\n");
        }
    }
    @Override
    protected void checkCommand(String command) throws InvalidArgumentsException {
        if (!command.equals("parse")){
            System.out.println("Неверная команда: " + command);
            printUsage();
            throw new InvalidArgumentsException("Неверная команда\n");
        }
    }
    @Override
    protected void printUsage() {
        System.out.println("Использование: CsvParser parse <inputFile>");
    }
}
