package Parser;
import Exceptions.InvalidInputFileException;

public interface Parser {
    String[] parse(String path) throws InvalidInputFileException;
}

