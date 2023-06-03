package TaskModule1.MethodTwo;

public class Conditions {
    String text;

    public Conditions(String text) {
        this.text = text;
    }

    public boolean isContainsSpace() {
        if (text.contains(" "))
            return true;
        else
            return false;
    }

    public boolean isEndsWithPunctuationMark() {
        if (text.endsWith(".") || text.endsWith("!") || text.endsWith("?"))
            return true;
        else
            return false;
    }


}
