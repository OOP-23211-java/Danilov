package Utils;

/**
 * Расширяет {@link BaseArguments} и добавляет поле для пути к выходному файлу.
 * Класс используется для инкапсулирования аргументов в единую сущность для их дальнейшей удобной обработки.
 * @see BaseArguments
 */
public class ValidatedArguments extends BaseArguments {
    private String outputFile;

    /**
     * Инициализирует обьект с заданными значениями:
     * @param command    команда, переданная в аргументах командной строки
     * @param inputFile  путь к входному файлу
     * @param outputFile путь к выходному файлу
     */
    public ValidatedArguments(String command, String inputFile, String outputFile) {
        super(command, inputFile);
        this.outputFile = outputFile;
    }

    /**
     * Устанавливает поле, хранящее значение пути к выходному файлу.
     * @param outputFile путь к выходному файлу.
     */
    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * @return путь к выходному файлу.
     */
    public String getOutputFile() {
        return outputFile;
    }
}
