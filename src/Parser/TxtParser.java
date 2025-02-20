package Parser;
import Exceptions.InvalidInputFileException;
import Exceptions.EmptyFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Класс {@code TxtParser} имплементирует интерфейс {@link Parser} и необходим для парсинга .txt файлов
 * Данный класс предоставляет реализацию метода {@link #parse} для считывания содержимого
 * файла
 */
public class TxtParser implements Parser {
    /**
     * Метод parse парсит файллы формата .txt
     * Осуществяются проверки:
     *     Проверяется существование файла
     *     Проверяется, что передается файл, а не директория
     *     Проверка прав файла на чтение
     *     Проверка на пустой файл
     * @param path - путь к файлу который нужно обработать
     * @return массив всех строк файла
     * @throws InvalidInputFileException если файл не существует, является директорией, недоступен для чтения,
     *      либо возникает ошибка при открытии
     * @throws EmptyFileException только в случае, если файл пустой
     */
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

