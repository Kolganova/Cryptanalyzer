package TaskModule1.MethodTwo;

import TaskModule1.Decipher;
import TaskModule1.PunctuationException;

public class CypherWithoutKey implements Decipher {
    private final char[] alphabet;
    private final StringBuilder text;

    public CypherWithoutKey(char[] alphabet, StringBuilder text) {
        this.alphabet = alphabet;
        this.text = text;
    }

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
                try {
                    throw new PunctuationException("Текст не может быть расшифрован. Обратите внимание на правила пунктуации.");
                } catch (PunctuationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return decipheredText;
    }

    public static class Conditions {
        String text;

        public Conditions(String text) {
            this.text = text;
        }

        public boolean isContainsSpace() {
            return text.contains(" ");
        }

        public boolean isEndsWithPunctuationMark() {
            return text.endsWith(".") || text.endsWith("!") || text.endsWith("?");
        }

    }

}
