package Exceptions;

import java.io.FileNotFoundException;

public class InvalidInputFileException extends FileNotFoundException {
    public InvalidInputFileException(String message){
        super(message);
    }
}
