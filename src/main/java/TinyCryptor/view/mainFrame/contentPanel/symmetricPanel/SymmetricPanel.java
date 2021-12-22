package TinyCryptor.view.mainFrame.contentPanel.symmetricPanel;

import TinyCryptor.view.helper.InOutBoxPanel;
import TinyCryptor.view.helper.SetUpBoxPanel;

import javax.swing.*;
import java.awt.*;

public class SymmetricPanel extends JPanel {
    // fields
    private InOutPanel inOutPanel;
    private SetUpPanel setUpPanel;

    // constructor
    public SymmetricPanel() {
        this.setName("symmetric");
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

    public InOutBoxPanel getInitVecBox() {
        return inOutPanel.getInitVecBox();
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

    public JButton getShowKeyBtn() {
        return setUpPanel.getKeyBtn();
    }
}
