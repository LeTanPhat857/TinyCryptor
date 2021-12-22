package TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel;

import TinyCryptor.view.helper.InOutBoxPanel;

import javax.swing.*;

public class InOutPanel extends JPanel {
    // fields
    private InOutBoxPanel keyInOutBoxPanel;
    private InOutBoxPanel plainInOutBoxPanel;
    private InOutBoxPanel cipherInOutBoxPanel;

    // constructor
    public InOutPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        keyInOutBoxPanel = new InOutBoxPanel("Key (Base64)").hideFileProcessPanel();
        plainInOutBoxPanel = new InOutBoxPanel("Input").hideFileProcessPanel();
        cipherInOutBoxPanel = new InOutBoxPanel("Output (Base64)").blockTextArea().hideFileProcessPanel();

        this.add(keyInOutBoxPanel);
        this.add(plainInOutBoxPanel);
        this.add(cipherInOutBoxPanel);
    }

    // methods
    public InOutBoxPanel getKeyBox() {
        return keyInOutBoxPanel;
    }

    public InOutBoxPanel getPlainBox() {
        return plainInOutBoxPanel;
    }

    public InOutBoxPanel getCipherBox() {
        return cipherInOutBoxPanel;
    }
}


