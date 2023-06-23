package taskModule1.methodOne;

import taskModule1.Encrypt;
import taskModule1.Decipher;
import taskModule1.graphicalUserInterface.Gui;

public class CypherWithKey implements Encrypt, Decipher {

    private int key;
    private final char[] alphabet;
    private final StringBuilder text;

    public CypherWithKey(int key, char[] alphabet, StringBuilder text) {
        this.key = key;
        this.alphabet = alphabet;
        this.text = text;
    }

    @Override
    public String getDecipheredText() {
        if (key < 0)
            key *= (-1);
        String decipheredText = "";
        char currentChar;
        int lengthOfAlphabet = alphabet.length;
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

        return decipheredText;
    }

    @Override
    public String getEncryptedText() {
        if (key < 0)
            key *= (-1);
        String cipheredText = "";
        char currentChar;
        int lengthOfAlphabet = alphabet.length;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < lengthOfAlphabet; j++) {
                currentChar = text.charAt(i);
                if (Character.toLowerCase(currentChar) == alphabet[j]) {
                    if (j + key >= lengthOfAlphabet) {
                        currentChar = alphabet[j + key - lengthOfAlphabet];
                    } else {
                        currentChar = alphabet[j + key];
                    }
                    cipheredText += currentChar;
                    break;
                }
            }
        }

        return cipheredText;
    }

    public String getResult(Gui app) {
        if (app.getAction1Button().isSelected()) {
            return key > 0 ? getEncryptedText() : getDecipheredText();
        } else {
            return key > 0 ? getDecipheredText() : getEncryptedText();
        }
    }
}
