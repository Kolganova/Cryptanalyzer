package TaskModule1;

import TaskModule1.GraphicalUserInterface.Gui;
import TaskModule1.MethodOne.*;
import TaskModule1.MethodTwo.*;

import static TaskModule1.Alphabet.getAlphabet;
import static TaskModule1.MethodOne.Key.*;
import static TaskModule1.Paths.*;
import static TaskModule1.WorkerWithFile.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Gui app = new Gui("Шифр Цезаря");
        app.setVisible(true);

        getCurrentPathFromApp(app);

        if (app.getAction1Button().isSelected() || app.getAction2Button().isSelected()) {
            minMaxValueOfKeySetter(getAlphabet().length);
            setKeyToApp(app);

            try {
                readFromFile(getCurrentPath());
                CypherWithKey cypherWithKey = new CypherWithKey(getValueOfKey(), getAlphabet(), WorkerWithFile.getText());
                setNewPath("/fileResult");
                writeToFile(getNewPath(), cypherWithKey.getResult(app));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        app.setTextOfErrorsIsReady(true);

        if (app.getAction3Button().isSelected()) {
            try {
                readFromFile(getCurrentPath());
                CypherWithoutKey cypherWithoutKey = new CypherWithoutKey(getAlphabet(), WorkerWithFile.getText());
                writeToFile(getCurrentPath(), cypherWithoutKey.getDecipheredText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}