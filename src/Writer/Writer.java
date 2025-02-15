package Writer;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Writer {
    void write(List<Map.Entry<String, Integer>> frequencyList, String outputFile, int countWords) throws IOException;
}
