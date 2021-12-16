package TinyCryptor.view.mainFrame.contentPanel.symmetricPanel;

import TinyCryptor.view.mainFrame.contentPanel.helperPanel.InOutBoxPanel;

import javax.swing.*;

public class InOutPanel extends JPanel {
    // fields
    private InOutBoxPanel keyInOutBoxPanel;
    private InOutBoxPanel plainInOutBoxPanel;
    private InOutBoxPanel cipherInOutBoxPanel;
    private InOutBoxPanel initVecInOutBoxPanel;

    // constructor
    public InOutPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        keyInOutBoxPanel = new InOutBoxPanel("Key");
        plainInOutBoxPanel = new InOutBoxPanel("Input");
        cipherInOutBoxPanel = new InOutBoxPanel("Output (Base64)").blockChooseFileBtn().blockTextArea().setOpenFolderBtn();
        initVecInOutBoxPanel = new InOutBoxPanel("Init Vector (Optional)").setRow(1);

        this.add(keyInOutBoxPanel);
        this.add(plainInOutBoxPanel);
        this.add(cipherInOutBoxPanel);
        this.add(initVecInOutBoxPanel);
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

    public InOutBoxPanel getInitVecBox() {
        return initVecInOutBoxPanel;
    }
}


