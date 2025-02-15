package mytests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Utils.PunctuationRemover;
import Processors.Processor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTests {

    @Test
    public void testProcessorCountWords(){
        String[] lines = {"fgf fgfg, dfdf", "df wwee ttt", "545 233f$"};
        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> processedData = processor.process(lines);
        assertEquals(8, processedData.size());
    }

    @Test
    public void testProcessorWithPunctuation() {
        String[] lines = {"Hello, shvab!", "DU... DU!"};
        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> processedData = processor.process(lines);

        StringBuilder actualData = new StringBuilder();
        for (Map.Entry<String, Integer> entry : processedData) {
            actualData.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }

        String expectedData = "du: 2\nshvab: 1\nhello: 1";
        assertEquals(expectedData, actualData.toString().trim());
    }

    @Test
    public void testProcessorCaseInsensitive() {
        String[] lines = {"DGMA", "dgma", "DGma"};
        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> processedData = processor.process(lines);

        assertEquals(1, processedData.size());
        assertEquals("dgma", processedData.get(0).getKey());
        assertEquals(3, processedData.get(0).getValue());
    }

    @Test
    public void testProcessorSortByFrequency() {
        String[] lines = {"DgMa dgma", "du du dgma", "dgma"};
        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> processedData = processor.process(lines);

        assertEquals(2, processedData.size());
        assertEquals("dgma", processedData.get(0).getKey());
        assertEquals(4, processedData.get(0).getValue());
        assertEquals("du", processedData.get(1).getKey());
        assertEquals(2, processedData.get(1).getValue());
    }

    @Test
    public void testProcessorWithNumbersAndSymbols() {
        String[] lines = {"123 fizika2", "456 fizika2 789", "$%& fizika2"};
        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> processedData = processor.process(lines);

        assertEquals(4, processedData.size());
        assertEquals("fizika2", processedData.get(0).getKey());
        assertEquals(3, processedData.get(0).getValue());
    }

    @Test
    public void testProcessorLargeInput() {
        String[] lines = {"word ".repeat(1000)};
        Processor processor = new Processor();
        List<Map.Entry<String, Integer>> processedData = processor.process(lines);

        assertEquals(1, processedData.size());
        assertEquals("word", processedData.get(0).getKey());
        assertEquals(1000, processedData.get(0).getValue());
    }
}
