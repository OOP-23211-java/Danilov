package Exceptions;

/**
 * Исключение, выбрасываемое при обнаружении недопустимых аргументов
 * Бросается в случае, если входные аргументы не соответствуют ожидаемым(ому) формату или количеству
 * К примеру, оно бросается в классе Main
 */

public class InvalidArgumentsException extends Exception{
    public InvalidArgumentsException(String message){
        super(message);
    }
}
