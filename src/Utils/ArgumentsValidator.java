package Utils;

import Exceptions.InvalidArgumentsException;

/**
 * ArgumentsValidator отвечает за проверку аргументов командной строки.
 * Проверяет, что передается правильное число аргументов и что команда соответствует ожидаемой.
 * В случае несоответствий бросается {@link InvalidArgumentsException} и соответствующее сообщение.
 * @see BaseArgumentsValidator
 * @see ValidatedArguments
 * @see InvalidArgumentsException
 */
public class ArgumentsValidator extends BaseArgumentsValidator {
    /**
     * Проверяет аргументы командной строки.
     * @param args массив аргументов вида "command", "inputPath", "outPutPath".
     * @return объект ValidatedArguments с валидными параметрами.
     * @throws InvalidArgumentsException если количество аргументов меньше 3 или если команда не "parse"
     */
    public ValidatedArguments validate(String[] args) throws InvalidArgumentsException {
        checkArgsCount(args.length);

        String command = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        checkCommand(command);
        return new ValidatedArguments(command, inputFile, outputFile);
    }

    /**
     * Проверяет, что число аргументов соответствует ожидаемому.
     * @param count - количество аргументов
     * @throws InvalidArgumentsException - если аргументов меньше 3.
     */
    @Override
    protected void checkArgsCount(int count) throws InvalidArgumentsException {
        if (count < 3){
            printUsage();
            throw new InvalidArgumentsException("Неверное количество аргументов\n");
        }
    }

    /**
     * Проверяет, что команда соответствует ("parse").
     * @param command - команда.
     * @throws InvalidArgumentsException - если команда не "parse".
     */
    @Override
    protected void checkCommand(String command) throws InvalidArgumentsException {
        if (!command.equals("parse")){
            System.out.println("Неверная команда: " + command);
            printUsage();
            throw new InvalidArgumentsException("Неверная команда\n");
        }
    }
    /**
     * Выводит информацию о правильном использовании.
     */
    @Override
    protected void printUsage() {
        System.out.println("Использование: CsvParser parse <inputFile>");
    }
}
