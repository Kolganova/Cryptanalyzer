package TaskModule1.MethodOne;

public class Key {
    private int value;
    private int MAX_VALUE;
    private int MIN_VALUE;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isKeyValid() {

        return (value >= MIN_VALUE && value <= MAX_VALUE);
    }

    public void MinMaxSetter(int alphabetLength) {
        this.MAX_VALUE = alphabetLength - 1;
        this.MIN_VALUE = (alphabetLength - 1) * -1;
    }

    public int getMAX_VALUE() {
        return MAX_VALUE;
    }

    public int getMIN_VALUE() {
        return MIN_VALUE;
    }
}
