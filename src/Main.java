import Exceptions.InvalidArgumentsException;
import Exceptions.InvalidInputFileException;
import Parser.Parser;
import Parser.TxtParser;
import Processors.Processor;
import Utils.ArgumentsValidator;
import Utils.ValidatedArguments;
import Writer.Writer;
import Writer.CSVWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import Utils.BaseArgumentsValidator;
import Utils.BaseArguments;

/**
 * Программа выполняет следующие шаги:
 *   Валидация аргументов командной строки.
 *   Парсится входной файла с помощью {@link TxtParser}.
 *   Обрабатывается текс с подсчетом частоты слов внутри метода process класса {@link Processor}.
 *   Записывается результат в выходной CSV файл с помощью метода write класса {@link CSVWriter}.
 * В случае возникновения ошибок
 * выводятся соответствующие сообщения.
 */

class Main{
    /**
     * @param args - аргументы командной строки, где:
     *      args[0] - команда
     *      args[1] - путь к входному файлу
     *      args[2] - путь к выходному файлу
     */
    public static void main(String[] args) {
        try{
            final BaseArguments validatedArgs = validateArgs(args);
            final String inputFilePath = validatedArgs.getInputFile();
            final String outputFilePath = ((ValidatedArguments)validatedArgs).getOutputFile();

            final String[] lines = parse(inputFilePath);

            final Processor processor = new Processor();
            final List<Map.Entry<String, Integer>> frequencyList = processor.process(lines);
            final int totalWords = processor.getCountWords();

            writeData(frequencyList, outputFilePath, totalWords);
            System.out.println("Completed.\nTotal words: " + totalWords);

        } catch(InvalidArgumentsException e){
            System.out.println("Ошибка аргументов коммандной строки: " + e.getMessage());
        } catch(InvalidInputFileException e){
            System.out.println("Недопустимый input файл: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        } catch (ClassCastException e){
            System.out.println("Ошибка приведение типов: " + e.getMessage());
        }
    }

    /**
     * Метод-обертка для валидации аргументов через ArgumentsValidator
     * @param args аргументы командной строки
     * @return объект {@link BaseArguments} с провалидированными параметрами
     * @throws InvalidArgumentsException если аргументы не соответствуют ожиданиям
     */
    private static BaseArguments validateArgs(String[] args) throws InvalidArgumentsException {
        BaseArgumentsValidator validator = new ArgumentsValidator();
        return validator.validate(args);
    }

    /**
     * Метод-обертка для парсинга переданного файла при помощи TxtParser
     * @param inputFilePath путь к входному файлу
     * @return массив строк файла
     * @throws InvalidInputFileException если файл не существует или недоступен для чтения
     */
    private static String[] parse(String inputFilePath) throws InvalidInputFileException{
        Parser parser = new TxtParser();
        return parser.parse(inputFilePath);
    }
    /**
     * Метод-оберкта который аписывает результаты в выходной файл при помощи CSVWriter.
     * @param wordsAndFreqsPairs список пар <слово ; частота>
     * @param outputFilePath путь к выходному файлу
     * @param totalWordsCount общее количество слов в файле input
     * @throws IOException если происходит ошибка ввода вывода при записи
     */
    private static void writeData(List<Map.Entry<String, Integer>> wordsAndFreqsPairs, String outputFilePath, int totalWordsCount)
            throws IOException {
        Writer writer = new CSVWriter();
        writer.write(wordsAndFreqsPairs, outputFilePath, totalWordsCount);
    }
}
