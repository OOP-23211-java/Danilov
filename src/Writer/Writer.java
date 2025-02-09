package Writer;
import java.util.List;
import java.util.Map;

public interface Writer {
    void write(List<Map.Entry<String, Integer>> frequencyList, int countWords);
}
