package TaskModule1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class WorkerWithFile {
    private static final StringBuilder text = new StringBuilder();

    public static void readFromFile(Path path) throws IOException {
        List<String> list = Files.readAllLines(path);
        for (String temp : list) {
            text.append(temp);
        }
    }

    public static void writeToFile(Path path, String text) throws IOException {
        Files.writeString(path, text, StandardCharsets.UTF_8);
    }

    public static StringBuilder getText() {
        return text;
    }

}
