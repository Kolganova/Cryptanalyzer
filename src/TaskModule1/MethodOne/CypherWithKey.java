package TaskModule1.MethodOne;

import TaskModule1.Encrypt;
import TaskModule1.Decipher;

public class CypherWithKey implements Encrypt, Decipher {

    private int key;
    private char[] alphabet;
    private StringBuilder text;

    @Override
    public String getDecipheredText() {
        String decipheredText = "";
//        boolean isUpperCase;
        char currentChar;
        int lengthOfAlphabet = alphabet.length;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < lengthOfAlphabet; j++) {
                currentChar = text.charAt(i);
//                на случай если нужно не учитывать пробелы:
//                if (currentChar == ' ')
//                    continue;
//                на случай если нужно учитывать регистр:
//                isUpperCase = Character.isUpperCase(currentChar);
                if (Character.toLowerCase(currentChar) == alphabet[j]) {
                    if (j - key < 0) {
                        currentChar = alphabet[lengthOfAlphabet + j - key];
                    } else {
                        currentChar = alphabet[j - key];
                    }
//                    if (isUpperCase)
//                        currentChar = Character.toUpperCase(currentChar);
                    decipheredText += currentChar;
                    break;
                }
            }
        }

        return decipheredText;
    }

    @Override
    public String getEncryptedText() {
        String cipheredText = "";
        //boolean isUpperCase = false;
        char currentChar;
        int lengthOfAlphabet = alphabet.length;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < lengthOfAlphabet; j++) {
                currentChar = text.charAt(i);
//                на случай если нужно не учитывать пробелы:
//                if (currentChar == ' ')
//                    continue;
//                isUpperCase = Character.isUpperCase(currentChar);
                if (Character.toLowerCase(currentChar) == alphabet[j]) {
                    if (j + key >= lengthOfAlphabet) {
                        currentChar = alphabet[j + key - lengthOfAlphabet];
                    } else {
                        currentChar = alphabet[j + key];
                    }
//                    if (isUpperCase)
//                        currentChar = Character.toUpperCase(currentChar);
                    cipheredText += currentChar;
                    break;
                }
            }
        }

        return cipheredText;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setAlphabet(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public int getKey() {
        return key;
    }

    public void changeMarkOnKey() {
        setKey(-1 * key);
    }
}
