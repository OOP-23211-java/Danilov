package Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class TxtParser implements Parser {
    @Override
    public String[] parse(String path) {
        List<String> strings = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(path))) {
            while(scanner.hasNextLine()){
                strings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }

        return strings.toArray(new String[strings.size()]);
    }
}

