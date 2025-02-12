import Parser.TxtParser;
import Writer.CsvWriter;
import java.util.List;
import java.util.Map;
import Utils.IArgumentsValidator;
import Utils.ArgumentsValidator;
import Utils.BaseArguments;

class Main{
    public static void main(String[] args) {
        IArgumentsValidator validator = new ArgumentsValidator();
        BaseArguments validatedArgs = validator.validate(args);
        String inputFile = validatedArgs.getInputFile();

        TxtParser parser = new TxtParser();
        String[] lines = parser.parse(inputFile);

        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> frequencyList = processor.process(lines);
        int totalWords = processor.getCountWords();

        CsvWriter writer = new CsvWriter();
        writer.write(frequencyList, totalWords);

        System.out.println("Completed\n");
        System.out.println("Total words: " + totalWords);
    }
}
