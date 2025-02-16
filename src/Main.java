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

class Main{
    public static void main(String[] args) {
        try{
            BaseArguments validatedArgs = validateArgs(args);
            String inputFilePath = validatedArgs.getInputFile();
            String outputFilePath = ((ValidatedArguments)validatedArgs).getOutputFile();

            String[] lines = parse(inputFilePath);

            Processor processor = new Processor();
            List<Map.Entry<String, Integer>> frequencyList = processor.process(lines);
            int totalWords = processor.getCountWords();

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

    private static BaseArguments validateArgs(String[] args) throws InvalidArgumentsException {
        BaseArgumentsValidator validator = new ArgumentsValidator();
        return validator.validate(args);
    }

    private static String[] parse(String inputFilePath) throws InvalidInputFileException{
        Parser parser = new TxtParser();
        return parser.parse(inputFilePath);
    }

    private static void writeData(List<Map.Entry<String, Integer>> Data, String outputFilePath, int totalWords) throws IOException {
        Writer writer = new CSVWriter();
        writer.write(Data, outputFilePath, totalWords);
    }
}
