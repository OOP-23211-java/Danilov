package Exceptions;

/**
 * Исключение, выбрасываемое когда входной файл оказывается пустым.
 * Наследуется от {@link InvalidInputFileException}
 */
public class EmptyFileException extends InvalidInputFileException {
    public EmptyFileException(String message) {
        super(message);
    }

}
