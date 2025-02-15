package Parser;
import Exceptions.InvalidInputFileException;
import Exceptions.EmptyFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class TxtParser implements Parser {
    @Override
    public String[] parse(String path) throws InvalidInputFileException {
        File inputFile = new File(path);

        if (!inputFile.exists()) {
            throw new InvalidInputFileException("Файл не существует\n");
        }
        if (inputFile.isDirectory()) {
            throw new InvalidInputFileException("Передано имя директории, а не файла\n");
        }
        if (!inputFile.canRead()) {
            throw new InvalidInputFileException("Нет прав на чтение файла\n");
        }
        if (inputFile.length() == 0) {
            throw new EmptyFileException("Input файл пустой\n");
        }

        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new InvalidInputFileException("Ошибка при открытии файла\n");
        }

        return strings.toArray(new String[strings.size()]);
    }
}

