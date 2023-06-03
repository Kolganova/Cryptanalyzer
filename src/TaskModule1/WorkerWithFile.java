package TaskModule1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkerWithFile {
    private static List<String> list;
    private static StringBuilder text = new StringBuilder();

    public static void readFromFile(Path path) throws IOException {
        list = Files.readAllLines(path);
        for (int i = 0; i < list.size(); i++) {
            text.append(list.get(i));
        }
    }

    public static void writeToFile(Path path, String text) throws IOException {
        Files.writeString(path, text, StandardCharsets.UTF_8);
    }

    public static StringBuilder getText() {
        return text;
    }

}
