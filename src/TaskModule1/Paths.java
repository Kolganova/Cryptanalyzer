package TaskModule1;

import TaskModule1.GraphicalUserInterface.Gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

public class Paths {
    private static String nameOfCurrentPath;
    private static String nameOfNewPath;
    private static Path currentPath;
    private static Path newPath;
    private static String fileExtension = "";

    public static void setNameOfCurrentPath(String name) {
        nameOfCurrentPath = name;
    }

    public static void setNameOfNewPath(String name) {
        nameOfNewPath = name;
    }

    public static void setCurrentPath() {
        currentPath = Path.of(nameOfCurrentPath);
    }

    public static void setNewPath() throws IOException {
        newPath = Files.createFile(Path.of(nameOfNewPath));

    }

    public static void setFileExtension() {
        if (nameOfCurrentPath.contains(".")) {
            int indexOfDot = nameOfCurrentPath.indexOf('.');
            String fileExtensionName = nameOfCurrentPath.substring(indexOfDot);

            fileExtension = fileExtensionName;
        }
    }

    public static String getFileExtension() {
        return fileExtension;
    }

    public static Path getNewPath() {
        return newPath;
    }

    public static Path getCurrentPath() {
        return currentPath;
    }

    public static void setCurrentPathToApp(Gui app) {

        try {

            while (app.getPath() == null) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            setNameOfCurrentPath(app.getPath());
            setCurrentPath();

            if ("".equals(currentPath.toString())) {
                throw new PathFieldIsEmptyException("Введите абсолютный путь!");
            } else if (!(currentPath.isAbsolute())) {
                throw new NotAbsolutePathException("Путь не является абсолютным. Введите абсолютный путь.");
            }
            if (!(Files.exists(currentPath))) {
                throw new FileNotExistException("Файл не найден. Введите верный абсолютный путь.");
            }

        } catch (NotAbsolutePathException | FileNotExistException | PathFieldIsEmptyException e) {
            app.setTextOfErrors(e.getMessage() + "\n");
            app.incrementErrorCounter();
        } catch (InterruptedException ie) {
            System.out.println("что-то пошло не так в блоке TaskModule1.Main - Paths");
        }
    }

}
