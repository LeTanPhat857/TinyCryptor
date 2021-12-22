package TinyCryptor.view.mainFrame.contentPanel.symmetricPanel;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.RoundedButton;
import TinyCryptor.view.helper.SetUpBoxPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SetUpPanel extends JPanel {
    // fields
    private JRadioButton encrypt;
    private JRadioButton decrypt;
    private SetUpBoxPanel algorithmSetUpBoxPanel;
    private SetUpBoxPanel keySizeSetUpBoxPanel;
    private SetUpBoxPanel modeSetUpBoxPanel;
    private SetUpBoxPanel paddingSetUpBoxPanel;
    private JButton newKeyBtn;
    private JButton keyBtn;

    // constructor
    public SetUpPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Set up"));
        // create component
        encrypt = new JRadioButton("Encrypt", true);
        decrypt = new JRadioButton("Decrypt");
        algorithmSetUpBoxPanel = new SetUpBoxPanel("Algorithm");
        modeSetUpBoxPanel = new SetUpBoxPanel("Mode");
        paddingSetUpBoxPanel = new SetUpBoxPanel("Padding");
        keySizeSetUpBoxPanel = new SetUpBoxPanel("");
        // add component
        this.add(createEncryptDecryptPanel());
        this.add(algorithmSetUpBoxPanel);
        this.add(modeSetUpBoxPanel);
        this.add(paddingSetUpBoxPanel);
        this.add(createKeySizePanel());
    }

    // methods
    // helper to create view
    private JPanel createEncryptDecryptPanel() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(encrypt);
        buttonGroup.add(decrypt);
        encrypt.setBackground(Color.WHITE);
        decrypt.setBackground(Color.WHITE);
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2, 15, 10));
        tempPanel.setBorder(new EmptyBorder(15, 15, 25, 15));
        tempPanel.add(encrypt);
        tempPanel.add(decrypt);
        return tempPanel;
    }

    private JPanel createKeySizePanel() {
        JPanel keySizePanel = new JPanel();
        keySizePanel.setBackground(Color.WHITE);
        keySizePanel.setLayout(new BoxLayout(keySizePanel, BoxLayout.Y_AXIS));
        keySizePanel.setBorder(BorderFactory.createTitledBorder("Create key (bits)"));

        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2, 10, 0));
        tempPanel.setBorder(new EmptyBorder(0, 5, 5, 5));

        newKeyBtn = new RoundedButton();
        keyBtn = new RoundedButton();

        newKeyBtn.setIcon(Utils.getImageIcon("images/icon/new.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        keyBtn.setIcon(Utils.getImageIcon("images/icon/key.png", 16, 16, Image.SCALE_AREA_AVERAGING));

        newKeyBtn.setToolTipText("create new key");
        keyBtn.setToolTipText("show key");

        tempPanel.add(newKeyBtn);
        tempPanel.add(keyBtn);

        keySizePanel.add(keySizeSetUpBoxPanel);
        keySizePanel.add(tempPanel);
        return keySizePanel;
    }

    // public get
    public JRadioButton getEncrypt() {
        return encrypt;
    }

    public JRadioButton getDecrypt() {
        return decrypt;
    }

    public SetUpBoxPanel getAlgorithmBox() {
        return this.algorithmSetUpBoxPanel;
    }

    public SetUpBoxPanel getKeySizeBox() {
        return keySizeSetUpBoxPanel;
    }

    public SetUpBoxPanel getModeBox() {
        return modeSetUpBoxPanel;
    }

    public SetUpBoxPanel getPaddingBox() {
        return paddingSetUpBoxPanel;
    }

    public JButton getNewKeyBtn() {
        return newKeyBtn;
    }

    public JButton getKeyBtn() {
        return keyBtn;
    }
}