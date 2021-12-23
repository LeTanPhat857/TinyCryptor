package TinyCryptor.view.mainFrame.contentPanel.PBEPanel;

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
    private SetUpBoxPanel hashAlgorithmSetUpBoxPanel;
    private SetUpBoxPanel symmetricAlgorithmSetUpBoxPanel;
    private JButton newKeyBtn;
    private JButton keyBtn;

    // constructor
    public SetUpPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Set up"), BorderFactory.createEmptyBorder(0, 0, 100, 0)));
        // create component
        encrypt = new JRadioButton("Encrypt", true);
        decrypt = new JRadioButton("Decrypt");
        hashAlgorithmSetUpBoxPanel = new SetUpBoxPanel("Hash Algorithm").setToolTip("choose hash algorithm");
        symmetricAlgorithmSetUpBoxPanel = new SetUpBoxPanel("Symmetric Algorithm").setToolTip("choose symmetric algorithm");
        // add component
        this.add(createEncryptDecryptPanel());
        this.add(hashAlgorithmSetUpBoxPanel);
        this.add(symmetricAlgorithmSetUpBoxPanel);
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
        keySizePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Create salt"), BorderFactory.createEmptyBorder(30, 0, 0, 0)));

        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2, 10, 0));
        tempPanel.setBorder(new EmptyBorder(0, 5, 5, 5));

        newKeyBtn = new RoundedButton();
        keyBtn = new RoundedButton();

        newKeyBtn.setIcon(Utils.getImageIcon("images/icon/new.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        keyBtn.setIcon(Utils.getImageIcon("images/icon/key.png", 16, 16, Image.SCALE_AREA_AVERAGING));

        newKeyBtn.setToolTipText("create salt");
        keyBtn.setToolTipText("show salt");

        tempPanel.add(newKeyBtn);
        tempPanel.add(keyBtn);

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

    public SetUpBoxPanel getHashAlgorithmBox() {
        return this.hashAlgorithmSetUpBoxPanel;
    }

    public SetUpBoxPanel getSymmetricAlgorithmBox() {
        return symmetricAlgorithmSetUpBoxPanel;
    }

    public JButton getNewKeyBtn() {
        return newKeyBtn;
    }

    public JButton getKeyBtn() {
        return keyBtn;
    }
}