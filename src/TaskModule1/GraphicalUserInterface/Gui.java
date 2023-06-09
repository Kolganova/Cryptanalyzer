package TaskModule1.GraphicalUserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {
    private JPanel mainPanel;
    private JLabel requestToAction;
    private JRadioButton action1Button;
    private JRadioButton action2Button;
    private JRadioButton action3Button;
    private JLabel requestToPath;
    private JTextArea pathToFile;
    private JLabel requestToKey;
    private JTextField valueOfKey;
    private JButton resultButton;
    private JTextPane GreetingsAndInstructions;
    private String path;
    private String key;
    private String textOfErrors = "";
    private byte errorCounter = 0;
    private boolean isTextOfErrorsIsReady;

    public Gui(String title) {
        super(title);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        action1Button.setSelected(true);

        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPath(pathToFile.getText());
                setKey(valueOfKey.getText());

                while (!isTextOfErrorsIsReady) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (errorCounter == 0) {
                    if (action1Button.isSelected() || action2Button.isSelected()) {
                        JOptionPane.showMessageDialog(mainPanel, "Файл был успешно создан!\n" +
                                        "Вы можете найти его в той же директории под именем: \"fileResult\".",
                                "Ура, все получилось!", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, "Файл был успешно перезаписан!",
                                "Ура, мы все взломали!", JOptionPane.PLAIN_MESSAGE);
                    }
                } else
                    JOptionPane.showMessageDialog(mainPanel, "Результат не может быть получен!\n\n" +
                                    textOfErrors + "\nДля повторной попытки закройте программу и запутстие код вновь.",
                            "Что-то пошло не так...", JOptionPane.WARNING_MESSAGE);
            }
        });

        action3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valueOfKey.setEnabled(false);
            }
        });
    }

    public JRadioButton getAction1Button() {
        return action1Button;
    }

    public JRadioButton getAction2Button() {
        return action2Button;
    }

    public JRadioButton getAction3Button() {
        return action3Button;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTextOfErrors(String textOfErrors) {
        this.textOfErrors += textOfErrors;
    }

    public void incrementErrorCounter() {
        errorCounter++;
    }

    public void setTextOfErrorsIsReady(boolean textOfErrorsIsReady) {
        isTextOfErrorsIsReady = textOfErrorsIsReady;
    }
}

