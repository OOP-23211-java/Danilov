package Exceptions;

import java.io.FileNotFoundException;

/**
 * Бросается, если входной файл не существует, является директорией или
 * недоступен для чтения. Это исключение расширяет {@link java.io.FileNotFoundException}.
 */

public class InvalidInputFileException extends FileNotFoundException {
    public InvalidInputFileException(String message){
        super(message);
    }
}
