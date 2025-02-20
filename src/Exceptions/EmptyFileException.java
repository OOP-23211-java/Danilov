package Exceptions;

/**
 * Исключение, выбрасываемое когда входной файл оказывается пустым.
 * Наследуется от {@link InvalidInputFileException} используется в методе
 * класса {@link Parser.TxtParser}
 */
public class EmptyFileException extends InvalidInputFileException {
    public EmptyFileException(String message) {
        super(message);
    }

}
