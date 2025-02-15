package mytests;

import Exceptions.InvalidInputFileException;
import Exceptions.EmptyFileException;
import Exceptions.InvalidArgumentsException;
import Parser.Parser;
import Parser.TxtParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Utils.ArgumentsValidator;
import Utils.BaseArguments;
import Utils.ValidatedArguments;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArgumentsValidatorAndParserTests {
    @Test
    public void testCommandDoesNotExist() {
        String command = "eat";
        String[] args = {command, "input.txt", "output.csv"};

        ArgumentsValidator validator = new ArgumentsValidator();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> validator.validate(args),
                "Ожидаемое исключение InvalidArgumentsException не было выброшено");

        assertEquals("Неверная команда\n", exception.getMessage(),
                "Неверное сообщение об ошибке при некорректной команде");
    }

    @Test
    public void testCountOfArgsDoesNotExist() {
        String[] args = {"parse", "input.txt"};

        ArgumentsValidator validator = new ArgumentsValidator();
        Exception exception = assertThrows(InvalidArgumentsException.class, () -> validator.validate(args),
                "Ожидаемое исключение InvalidArgumentsException не было выброшено");

        assertEquals("Неверное количество аргументов\n", exception.getMessage(),
                "Неверное сообщение об ошибке при некорректном количестве аргументов");
    }

    @Test
    public void testValidateReturnsValidatedArgs(){
        String[] args = {"parse", "input.txt", "output.csv"};
        ArgumentsValidator validator = new ArgumentsValidator();
        BaseArguments arguments;

        arguments = assertDoesNotThrow(() -> validator.validate(args), "validate должен вернуть объект и не выбрасывать исключений");
        assertNotNull(arguments, "validate не должен возвращать null");
        assertInstanceOf(ValidatedArguments.class, arguments, "Ожидался объект ValidatedArguments");
        assertEquals("parse", arguments.getCommand(), "Команда должна быть 'parse'");
        assertEquals("input.txt", arguments.getInputFile(), "Путь к input файлу должен быть 'input.txt'");
    }

    @Test
    public void testTxtParsersEmptyFileException(){
        File inputFile = new File("input.txt");

        assertDoesNotThrow(() -> inputFile.createNewFile(), "Не удалось создать тестовый файл");

        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("");
        } catch (IOException e) {
            fail("Ошибка при записи в файл");
        }

        Parser txtParser = new TxtParser();

        assertThrows(EmptyFileException.class, () -> txtParser.parse(inputFile.getPath()),
                "Ожидаемое исключение EmptyFileException не было выброшено");

        inputFile.delete();
    }

    @Test
    public void testFileNotExistException() {
        File file = new File("nonExistingFile.txt");

        if (file.exists()) {
            file.delete();
        }

        Parser parser = new TxtParser();
        InvalidInputFileException exception = assertThrows(InvalidInputFileException.class,
                () -> parser.parse(file.getPath()), "Ожидаемое исключение InvalidInputFileException не было выброшено");

        assertEquals("Файл не существует\n", exception.getMessage(), "Неверное сообщение в конструкторе InvalidInputFileException");
    }

    @Test
    public void testDirectoryException() {
        File directory = new File("testDirectory");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Parser parser = new TxtParser();
        InvalidInputFileException exception = assertThrows(InvalidInputFileException.class,
                () -> parser.parse(directory.getPath()), "Ожидаемое исключение InvalidInputFileException не было выброшено");

        assertEquals("Передано имя директории, а не файла\n", exception.getMessage(),
                "Неверное сообщение в конструкторе InvalidInputFileException");

        directory.delete();
    }

    @Test
    public void testNoReadPermissionException() {
        File file = new File("noReadFile.txt");
        if (!file.exists()) {
            assertDoesNotThrow(() -> file.createNewFile(), "Вызвалось исключение при создании тестового файла");
        }
        file.setReadable(false);

        Parser parser = new TxtParser();
        InvalidInputFileException exception = assertThrows(InvalidInputFileException.class,
                () -> parser.parse(file.getPath()), "Ожидаемое исключение InvalidInputFileException не было выброшено");

        assertEquals("Нет прав на чтение файла\n", exception.getMessage());
        file.delete();
    }

    @Test
    public void testParseFileContent() throws IOException{
        String testFilePath = "testfile.txt";
        String content = "line 1\nline 2\nline 3";
        File inputFile = new File(testFilePath);

        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write(content);
        }

        TxtParser parser = new TxtParser();

        String[] result = parser.parse(testFilePath);

        String[] expected = {"line 1", "line 2", "line 3"};

        assertArrayEquals(expected, result, "Неверная работа парсинга файла");

        inputFile.delete();
    }
}
