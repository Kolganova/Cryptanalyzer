package TaskModule1;

import TaskModule1.GraphicalUserInterface.Gui;
import TaskModule1.MethodOne.*;
import TaskModule1.MethodTwo.*;

import static TaskModule1.Alphabet.getAlphabet;
import static TaskModule1.MethodOne.Key.*;
import static TaskModule1.Paths.*;
import static TaskModule1.WorkerWithFile.readFromFile;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Gui app = new Gui("Шифр Цезаря");
        app.setVisible(true);

        setCurrentPathToApp(app);

        minMaxValueSetter(getAlphabet().length);

        if (app.getAction1Button().isSelected() || app.getAction2Button().isSelected()) {

            setKeyToApp(app);

            try {
                readFromFile(getCurrentPath());

                CypherWithKey cypherWithKey = new CypherWithKey();
                cypherWithKey.setKey(getValueOfKey());
                cypherWithKey.setText(WorkerWithFile.getText());
                cypherWithKey.setAlphabet(getAlphabet());
                setFileExtension();
                setNameOfNewPath(getCurrentPath().getParent() + "/fileResult" + getFileExtension());

                setNewPath();

                if (app.getAction1Button().isSelected()) {
                    if (cypherWithKey.getKey() > 0)
                        WorkerWithFile.writeToFile(getNewPath(), cypherWithKey.getEncryptedText());
                    else {
                        cypherWithKey.changeMarkOnKey();
                        WorkerWithFile.writeToFile(getNewPath(), cypherWithKey.getDecipheredText());
                    }
                } else if (app.getAction2Button().isSelected()) {
                    if (cypherWithKey.getKey() > 0)
                        WorkerWithFile.writeToFile(getNewPath(), cypherWithKey.getDecipheredText());
                    else {
                        cypherWithKey.changeMarkOnKey();
                        WorkerWithFile.writeToFile(getNewPath(), cypherWithKey.getEncryptedText());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("что-то пошло не так в блоке TaskModule1.Main - WorkerWithFile, button1 | button2");
            }
        }

        app.setTextOfErrorsIsReady(true);

        if (app.getAction3Button().isSelected()) {
            try {
                readFromFile(getCurrentPath());

                CypherWithoutKey cypherWithoutKey = new CypherWithoutKey();

                cypherWithoutKey.setText(WorkerWithFile.getText());
                cypherWithoutKey.setAlphabet(getAlphabet());
                WorkerWithFile.writeToFile(getCurrentPath(), cypherWithoutKey.getDecipheredText());
            } catch (IOException e) {
                System.out.println("что-то пошло не так в блоке TaskModule1.Main - WorkerWithFile, button3");
            }
        }
    }

}