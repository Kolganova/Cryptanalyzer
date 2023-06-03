package TaskModule1.MethodTwo;

import TaskModule1.Deciphered;

public class CypherWithoutKey implements Deciphered {
    private int key;
    private char[] alphabet;
    private StringBuilder text;

    @Override
    public String getDecipheredText() {
        String decipheredText = "";
        char temp;
        int lengthOfAlphabet = alphabet.length;

        for (key = 1; key < alphabet.length; key++) {

            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < lengthOfAlphabet; j++) {
                    temp = text.charAt(i);
                    if (Character.toLowerCase(temp) == alphabet[j]) {
                        if (j - key < 0) {
                            temp = alphabet[lengthOfAlphabet + j - key];
                        } else {
                            temp = alphabet[j - key];
                        }
                        decipheredText += temp;
                        break;
                    }
                }
            }
            Conditions conditions = new Conditions(decipheredText);

            if (conditions.isEndsWithPunctuationMark() && conditions.isContainsSpace())
                return decipheredText;
            else {
                decipheredText = "";
            }
            if (key == alphabet.length - 1) {
                decipheredText = "Текст не может быть расшифрован. Обратите внимание на правила пунктуации.";
            }
        }
        return decipheredText;
    }

    public void setAlphabet(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }
}
