package TinyCryptor.view.mainFrame.contentPanel.PBEPanel;

import TinyCryptor.view.helper.InOutBoxPanel;
import TinyCryptor.view.helper.SetUpBoxPanel;

import javax.swing.*;
import java.awt.*;

public class PBEPanel extends JPanel {
    // fields
    private InOutPanel inOutPanel;
    private SetUpPanel setUpPanel;

    // constructor
    public PBEPanel() {
        this.setName("pbe");
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        inOutPanel = new InOutPanel();
        setUpPanel = new SetUpPanel();

        this.add(inOutPanel, BorderLayout.CENTER);
        this.add(setUpPanel, BorderLayout.EAST);
    }

    // methods
    // in out panel
    public InOutBoxPanel getPasswordBox() {
        return inOutPanel.getPasswordBox();
    }

    public InOutBoxPanel getSaltBox() {return inOutPanel.getSaltBox(); }

    public InOutBoxPanel getInputBox() {
        return inOutPanel.getInputBox();
    }

    public InOutBoxPanel getOutputBox() {
        return inOutPanel.getOutputBox();
    }

    // set up panel
    public JRadioButton getEncrypt() {
        return setUpPanel.getEncrypt();
    }

    public JRadioButton getDecrypt() {
        return setUpPanel.getDecrypt();
    }

    public SetUpBoxPanel getHashAlgorithmBox() {
        return setUpPanel.getHashAlgorithmBox();
    }

    public SetUpBoxPanel getSymmetricAlgorithmBox() {
        return setUpPanel.getSymmetricAlgorithmBox();
    }

    public JButton getCreateSaltBtn() {
        return setUpPanel.getNewKeyBtn();
    }

    public JButton getShowSaltBtn() {
        return setUpPanel.getKeyBtn();
    }
}
