package TaskModule1.MethodTwo;

import TaskModule1.Decipher;

public class CypherWithoutKey implements Decipher {
    private char[] alphabet;
    private StringBuilder text;

    @Override
    public String getDecipheredText() {
        int key;
        String decipheredText = "";
        char currentChar;
        int lengthOfAlphabet = alphabet.length;

        for (key = 1; key < alphabet.length; key++) {

            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < lengthOfAlphabet; j++) {
                    currentChar = text.charAt(i);
                    if (Character.toLowerCase(currentChar) == alphabet[j]) {
                        if (j - key < 0) {
                            currentChar = alphabet[lengthOfAlphabet + j - key];
                        } else {
                            currentChar = alphabet[j - key];
                        }
                        decipheredText += currentChar;
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
