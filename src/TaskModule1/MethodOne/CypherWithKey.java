package TaskModule1.MethodOne;

import TaskModule1.Ciphered;
import TaskModule1.Deciphered;

public class CypherWithKey implements Ciphered, Deciphered {

    private int key;
    private char[] alphabet;
    private StringBuilder text;

    @Override
    public String getDecipheredText() {
        String decipheredText = "";
//        boolean isUpperCase;
        char temp;
        int lengthOfAlphabet = alphabet.length;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < lengthOfAlphabet; j++) {
                temp = text.charAt(i);
//                на случай если нужно не учитывать пробелы:
//                if (temp == ' ')
//                    continue;
//                на случай если нужно учитывать регистр:
//                isUpperCase = Character.isUpperCase(temp);
                if (Character.toLowerCase(temp) == alphabet[j]) {
                    if (j - key < 0) {
                        temp = alphabet[lengthOfAlphabet + j - key];
                    } else {
                        temp = alphabet[j - key];
                    }
//                    if (isUpperCase)
//                        temp = Character.toUpperCase(temp);
                    decipheredText += temp;
                    break;
                }
            }
        }

        return decipheredText;
    }

    @Override
    public String getCipheredText() {
        String cipheredText = "";
        //boolean isUpperCase = false;
        char temp;
        int lengthOfAlphabet = alphabet.length;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < lengthOfAlphabet; j++) {
                temp = text.charAt(i);
//                на случай если нужно не учитывать пробелы:
//                if (temp == ' ')
//                    continue;
//                isUpperCase = Character.isUpperCase(temp);
                if (Character.toLowerCase(temp) == alphabet[j]) {
                    if (j + key >= lengthOfAlphabet) {
                        temp = alphabet[j + key - lengthOfAlphabet];
                    } else {
                        temp = alphabet[j + key];
                    }
//                    if (isUpperCase)
//                        temp = Character.toUpperCase(temp);
                    cipheredText += temp;
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
}
