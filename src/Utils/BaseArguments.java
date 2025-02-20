package Utils;

/**
 * Абстрактный класс для базовых аргументов командной строки
 * Этот класс содержит поля: команда и путь к входному файлу.
 * Класс расширяется для добавления новых аргументов.
 * @see ValidatedArguments
 */
public abstract class BaseArguments {
    private String command;
    private String inputFile;

    /**
     * Инициализирует объект заданными значениями command и inputFile.
     * @param command   строка команда переданная из командной строки
     * @param inputFile путь к входному файлу
     */
    public BaseArguments(String command, String inputFile) {
        this.command = command;
        this.inputFile = inputFile;
    }

    /**
     * Конструктор по умолчанию.
     */
    public BaseArguments() {}

    /**
     * @return возвращает значение поля command.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Устанавливает поле command.
     * @param command - команда, которую нужно установить.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return путь к входному файлу.
     */
    public String getInputFile() {
        return inputFile;
    }

    /**
     * Устанавливает путь к входному файлу.
     * @param inputFile - путь к входному файлу.
     */
    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
}
