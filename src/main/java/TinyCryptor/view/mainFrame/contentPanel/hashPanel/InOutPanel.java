package TinyCryptor.view.mainFrame.contentPanel.hashPanel;

import TinyCryptor.view.mainFrame.contentPanel.helperPanel.InOutBoxPanel;

import javax.swing.*;

public class InOutPanel extends JPanel {
    // fields
    private InOutBoxPanel plainInOutBoxPanel;
    private InOutBoxPanel cipherInOutBoxPanel;

    // constructor
    public InOutPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        plainInOutBoxPanel = new InOutBoxPanel("Input").hideFileProcessPanel();
        cipherInOutBoxPanel = new InOutBoxPanel("Output (Base64)").blockTextArea().hideFileProcessPanel();

        this.add(plainInOutBoxPanel);
        this.add(cipherInOutBoxPanel);
    }

    // methods
    public InOutBoxPanel getPlainBox() {
        return plainInOutBoxPanel;
    }

    public InOutBoxPanel getCipherBox() {
        return cipherInOutBoxPanel;
    }
}


