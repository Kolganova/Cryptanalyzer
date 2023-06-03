package TaskModule1.MethodOne;

public class Key {
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean validationOfKey(int alphabetLength) {

        return (value >= 0 && value < alphabetLength);
    }

}
