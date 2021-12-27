package TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel;

import TinyCryptor.view.helper.InOutBoxPanel;
import TinyCryptor.view.helper.SetUpBoxPanel;

import javax.swing.*;
import java.awt.*;

public class AsymmetricPanel extends JPanel {
    // fields
    private InOutPanel inOutPanel;
    private SetUpPanel setUpPanel;

    // constructor
    public AsymmetricPanel() {
        this.setName("asymmetric");
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        inOutPanel = new InOutPanel();
        setUpPanel = new SetUpPanel();

        this.add(inOutPanel, BorderLayout.CENTER);
        this.add(setUpPanel, BorderLayout.EAST);
    }

    // methods
    // in out panel
    public InOutBoxPanel getKeyBox() {
        return inOutPanel.getKeyBox();
    }

    public InOutBoxPanel getInputBox() {
        return inOutPanel.getPlainBox();
    }

    public InOutBoxPanel getOutputBox() {
        return inOutPanel.getCipherBox();
    }

    // set up panel
    public JRadioButton getEncrypt() {
        return setUpPanel.getEncrypt();
    }

    public JRadioButton getDecrypt() {
        return setUpPanel.getDecrypt();
    }

    public SetUpBoxPanel getAlgorithmBox() {
        return setUpPanel.getAlgorithmBox();
    }

    public SetUpBoxPanel getKeySizeBox() {
        return setUpPanel.getKeySizeBox();
    }

    public SetUpBoxPanel getModeBox() {
        return setUpPanel.getModeBox();
    }

    public SetUpBoxPanel getPaddingBox() {
        return setUpPanel.getPaddingBox();
    }

    public JButton getNewKeyBtn() {
        return setUpPanel.getNewKeyBtn();
    }

    public JButton getPublicKeyBtn() {
        return setUpPanel.getPublicKeyBtn();
    }

    public JButton getPrivateKeyBtn() {
        return setUpPanel.getPrivateKeyBtn();
    }
}
