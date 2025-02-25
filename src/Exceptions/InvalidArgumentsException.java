package Exceptions;

/**
 * Исключение, выбрасываемое при обнаружении недопустимых аргументов
 * Бросается в случае, если входные аргументы не соответствуют ожидаемым(ому) формату или количеству
 */

public class InvalidArgumentsException extends Exception{
    public InvalidArgumentsException(String message){
        super(message);
    }
}
