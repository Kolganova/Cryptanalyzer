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

    private static void setNameOfNewPath(String name) {
        setFileExtension();
        nameOfNewPath = currentPath.getParent() + name + fileExtension;
    }

    public static void setNewPath(String name) throws IOException {
        setNameOfNewPath(name);
        newPath = Files.createFile(Path.of(nameOfNewPath));
    }

    private static void setFileExtension() {
        if (nameOfCurrentPath.contains(".")) {
            int indexOfDot = nameOfCurrentPath.indexOf('.');

            fileExtension = nameOfCurrentPath.substring(indexOfDot);
        }
    }

    public static Path getNewPath() {
        return newPath;
    }

    public static Path getCurrentPath() {
        return currentPath;
    }

    public static void getCurrentPathFromApp(Gui app) {

        try {

            while (app.getPath() == null) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            nameOfCurrentPath = app.getPath();
            currentPath = Path.of(nameOfCurrentPath);

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
            ie.printStackTrace();
        }
    }

}
