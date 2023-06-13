package TaskModule1.MethodOne;

import TaskModule1.GraphicalUserInterface.Gui;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Key {
    private static int value;
    private static int MAX_VALUE;
    private static int MIN_VALUE;

    private static void setValue(int valueOfKey) {
        value = valueOfKey;
    }

    public static int getValueOfKey() {
        return value;
    }

    private static boolean isKeyValid() {

        return (value >= MIN_VALUE && value <= MAX_VALUE);
    }

    public static void minMaxValueSetter(int alphabetLength) {
        MAX_VALUE = alphabetLength - 1;
        MIN_VALUE = (alphabetLength - 1) * -1;
    }

    public static void setKeyToApp(Gui app) {
        try {
            while (app.getKey() == null) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            String keyValueString = app.getKey();

            if (Objects.equals(keyValueString, "") || keyValueString == null) {
                throw new KeyFieldIsEmptyException("Введите значение ключа!");
            }
            try {
                setValue(Integer.parseInt(keyValueString));
            } catch (NumberFormatException e) {
                throw new InvalidKeyException("Недостижимый ключ. Пожалуйста, введите число между " + MIN_VALUE + " и " + MAX_VALUE + ".");
            }
            if (!(isKeyValid())) {
                throw new InvalidKeyException("Недостижимый ключ. Пожалуйста, введите число между " + MIN_VALUE + " и " + MAX_VALUE + ".");
            }

        } catch (InvalidKeyException | NumberFormatException | KeyFieldIsEmptyException e) {
            app.setTextOfErrors(e.getMessage() + "\n");
            app.incrementErrorCounter();
        } catch (InterruptedException ie) {
            System.out.println("что-то пошло не так в блоке установки Key");
        }
    }
}
