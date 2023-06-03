package TaskModule1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Paths {
    private String nameOfCurrentPath;
    private String nameOfNewPath;
    private Path currentPath;
    private Path newPath;
    private String fileExtension = "";

    private String getNameOfCurrentPath() {
        return nameOfCurrentPath;
    }

    public void setNameOfCurrentPath(String nameOfCurrentPath) {
        this.nameOfCurrentPath = nameOfCurrentPath;
    }

    public String getNameOfNewPath() {
        return nameOfNewPath;
    }

    public void setNameOfNewPath(String nameOfNewPath) {
        this.nameOfNewPath = nameOfNewPath;
    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath() {
        this.currentPath = Path.of(this.getNameOfCurrentPath());
    }

    public Path getNewPath() {
        return newPath;
    }

    public void setNewPath() throws IOException {
        this.newPath = Files.createFile(Path.of(this.getNameOfNewPath()));

    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension() {
        if (this.nameOfCurrentPath.contains(".")){
            int indexOfDot = this.nameOfCurrentPath.indexOf('.');
            String fileExtension = this.nameOfCurrentPath.substring(indexOfDot);

            this.fileExtension = fileExtension;
        }
    }
}
