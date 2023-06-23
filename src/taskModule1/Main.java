package taskModule1;

import taskModule1.graphicalUserInterface.Gui;
import taskModule1.methodOne.*;
import taskModule1.methodTwo.*;

import static taskModule1.Alphabet.getAlphabet;
import static taskModule1.methodOne.Key.*;
import static taskModule1.Paths.*;
import static taskModule1.WorkerWithFile.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        menu();
    }

    private static void menu() {
        Gui app = new Gui("Шифр Цезаря");
        app.setVisible(true);

        getCurrentPathFromApp(app);
        try {
            readFromFile(getCurrentPath());
            if (app.getAction1Button().isSelected() || app.getAction2Button().isSelected()) {
                setKeyToApp(app, getAlphabet().length);
                CypherWithKey cypherWithKey = new CypherWithKey(getValueOfKey(), getAlphabet(), WorkerWithFile.getText());
                setNewPath("fileResult");
                writeToFile(getNewPath(), cypherWithKey.getResult(app));
            }

            if (app.getAction3Button().isSelected()) {
                CypherWithoutKey cypherWithoutKey = new CypherWithoutKey(getAlphabet(), WorkerWithFile.getText());
                writeToFile(getCurrentPath(), cypherWithoutKey.getResult(app));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            app.setTextOfErrorsIsReady(true);
        }
    }

}