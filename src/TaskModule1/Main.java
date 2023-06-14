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
        try {
            readFromFile(getCurrentPath());
            if (app.getAction1Button().isSelected() || app.getAction2Button().isSelected()) {
                setKeyToApp(app, getAlphabet().length);
                CypherWithKey cypherWithKey = new CypherWithKey(getValueOfKey(), getAlphabet(), WorkerWithFile.getText());
                setNewPath("/fileResult");
                writeToFile(getNewPath(), cypherWithKey.getResult(app));
            }
            app.setTextOfErrorsIsReady(true);

            if (app.getAction3Button().isSelected()) {
                CypherWithoutKey cypherWithoutKey = new CypherWithoutKey(getAlphabet(), WorkerWithFile.getText());
                writeToFile(getCurrentPath(), cypherWithoutKey.getResult(app));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}