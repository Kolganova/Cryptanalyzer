package TaskModule1;

import TaskModule1.GraphicalUserInterface.Gui;
import TaskModule1.MethodOne.CypherWithKey;
import TaskModule1.MethodOne.InvalidKeyException;
import TaskModule1.MethodOne.Key;
import TaskModule1.MethodOne.KeyFieldIsEmptyException;
import TaskModule1.MethodTwo.CypherWithoutKey;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Gui app = new Gui("Шифр Цезаря");
        app.setVisible(true);

        char[] charAlphabet = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'к', 'л', 'м', 'н',
                'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я',
                '.', ',', '"', '\'', ':', '-', '!', '?', ' '}; // если хотим не учитывать пробелы, то убрать соответствующий char

        Alphabet alphabet = new Alphabet(charAlphabet);
        Paths paths = new Paths();

        try {
            while (app.getPath() == null) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            paths.setNameOfCurrentPath(app.getPath());
            paths.setCurrentPath();

            if ("".equals(paths.getCurrentPath().toString())) {
                throw new PathFieldIsEmptyException("Введите абсолютный путь!");
            } else if (!(paths.getCurrentPath().isAbsolute())) {
                throw new NotAbsolutePathException("Путь не является абсолютным. Введите абсолютный путь.");
            }
            if (!(Files.exists(paths.getCurrentPath()))) {
                throw new FileNotExistException("Файл не найден. Введите верный абсолютный путь.");
            }
        } catch (NotAbsolutePathException | FileNotExistException | PathFieldIsEmptyException e) {
            app.setTextOfErrors(e.getMessage() + "\n");
            app.incrementErrorCounter();
        } catch (InterruptedException ie) {
            System.out.println("что-то пошло не так в блоке TaskModule1.Main - Paths");
        }


        Key key = new Key();

        if (app.getAction1Button().isSelected() || app.getAction2Button().isSelected()) {
            try {
                while (app.getKey() == null) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                String keyValueString = app.getKey();

                if (Objects.equals(keyValueString, "") || keyValueString == null) {
                    throw new KeyFieldIsEmptyException("Введите значение ключа!");
                }
                try {
                    key.setValue(Integer.parseInt(keyValueString));
                } catch (NumberFormatException e) {
                    throw new InvalidKeyException("Недостижимый ключ. Пожалуйста, введите число между 0 и " + alphabet.getAlphabet().length + ".");
                }
//                if (!(key.validationOfKey(alphabet.getAlphabet().length))) {
//                    throw new InvalidKeyException("Недостижимый ключ. Пожалуйста, введите число между 0 и " + alphabet.getAlphabet().length + ".");
//                }

            } catch (InvalidKeyException | NumberFormatException | KeyFieldIsEmptyException e) {
                app.setTextOfErrors(e.getMessage() + "\n");
                app.incrementErrorCounter();
            } catch (InterruptedException ie) {
                System.out.println("что-то пошло не так в блоке TaskModule1.Main - Key");
            }

            try {
                WorkerWithFile.readFromFile(paths.getCurrentPath());

                CypherWithKey cypherWithKey = new CypherWithKey();
                cypherWithKey.setKey(key.getValue());
                cypherWithKey.setText(WorkerWithFile.getText());
                cypherWithKey.setAlphabet(alphabet.getAlphabet());
                paths.setFileExtension();
                paths.setNameOfNewPath(paths.getCurrentPath().getParent() + "/fileResult" + paths.getFileExtension());

                paths.setNewPath();

                if (app.getAction1Button().isSelected()) {
                    WorkerWithFile.writeToFile(paths.getNewPath(), cypherWithKey.getEncryptedText());
                } else if (app.getAction2Button().isSelected()) {
                    WorkerWithFile.writeToFile(paths.getNewPath(), cypherWithKey.getDecipheredText());
                }
            } catch (IOException e) {
                System.out.println("что-то пошло не так в блоке TaskModule1.Main - WorkerWithFile, button1 | button2");
            }
        }

        app.setTextOfErrorsIsReady(true);

        if (app.getAction3Button().isSelected()) {
            try {
                WorkerWithFile.readFromFile(paths.getCurrentPath());

                CypherWithoutKey cypherWithoutKey = new CypherWithoutKey();

                cypherWithoutKey.setText(WorkerWithFile.getText());
                cypherWithoutKey.setAlphabet(alphabet.getAlphabet());
                WorkerWithFile.writeToFile(paths.getCurrentPath(), cypherWithoutKey.getDecipheredText());
            } catch (IOException e) {
                System.out.println("что-то пошло не так в блоке TaskModule1.Main - WorkerWithFile, button3");
            }
        }

    }

}
