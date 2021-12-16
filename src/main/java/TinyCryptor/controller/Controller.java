package TinyCryptor.controller;

import TinyCryptor.model.*;
import TinyCryptor.model.PBE.iPBEAlgorithm;
import TinyCryptor.model.asymmetric.iAsymmetricAlgorithm;
import TinyCryptor.model.hash.iHashAlgorithm;
import TinyCryptor.model.symmetric.iSymmetricAlgorithm;
import TinyCryptor.utils.Utils;
import TinyCryptor.view.View;
import TinyCryptor.view.helpFrame.HelpFrame;
import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.mainFrame.contentPanel.helperPanel.RoundedButton;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Base64;

public class Controller {
    // fields
    private Model model;
    private View view;

    // constructor
    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    // methods
    public static Controller create(Model model) {
        return new Controller(model);
    }

    public void init() {
        // command
        view.getInfoBtn().addActionListener(e -> info());
        view.getRunBtn().addActionListener(e -> run());
        view.getHelpBtn().addActionListener(e -> help());
        view.getExitBtn().addActionListener(e -> exit());
        //

        // show view
        view.setVisible(true);
    }

    // action handler
    public void info() {
        String info = "TinyCryptor is a small Java cryptography project. \n" +
                "Subject: Cryptography \n" +
                "Instructor: Phan Dinh Long \n" +
                "Owner: Le Tan Phat \n";

        JOptionPane.showMessageDialog(null, info, "About TinyCryptor", JOptionPane.NO_OPTION);
    }

    public void help(){
        JPanel selectedPanel = (JPanel) view.getContentPanel().getSelectedComponent();
        HelpFrame.createHelpFrame().open(selectedPanel.getName()).init();
    }

    public void exit() {
        JButton yesBtn = new RoundedButton();
        JButton noBtn = new RoundedButton();
        yesBtn.setText("Yes");
        noBtn.setText("No");
        Object[] options = {yesBtn, noBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
        optionPane.setMessage("Do you want to exit?");
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        Dialog dialog = optionPane.createDialog("Exit");

        yesBtn.addActionListener(a -> System.exit(0));
        noBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public void run() {
        JPanel selectedPanel = (JPanel) view.getContentPanel().getSelectedComponent();
        switch (selectedPanel.getName()) {
            case "symmetric": {
                runSymmetric(selectedPanel);
                break;
            }
            case "asymmetric": {
                runAsymmetric(selectedPanel);
                break;
            }
            case "hash": {
                runHash(selectedPanel);
                break;
            }
            case "pbe": {
                runPBE(selectedPanel);
                break;
            }
        }
    }

    private void runSymmetric(JPanel selectedPanel) {
        try {
            SymmetricPanel panel = (SymmetricPanel) selectedPanel;
            // set up info
            boolean encrypt = panel.getEncrypt().isSelected();
            String algorithm = (String) panel.getAlgorithmBox().getSelected();
            String mode = (String) panel.getModeBox().getSelected();
            String padding = (String) panel.getPaddingBox().getSelected();
            String spec = algorithm + "/" + mode + "/" + padding;
            // in out info
            byte[] inputText = panel.getInputBox().getText().getBytes("utf-8");
            byte[] key = panel.getKeyBox().getText().getBytes("utf-8");
            byte[] initVec = panel.getInitVecBox().getText().getBytes("utf-8");

            File keyFile = panel.getKeyBox().getFile();
            File inputFile = panel.getInputBox().getFile();
            File initVecFile = panel.getInitVecBox().getFile();
            // process
            if (keyFile != null) {
                key = Utils.readFile(keyFile);
            }
            if (inputFile != null) {
                inputText = Utils.readFile(inputFile);
            }
            if (initVecFile != null) {
                initVec = Utils.readFile(inputFile);
            }
            if (Arrays.equals(key, "".getBytes())) {
                throw new Exception("Empty key");
            }
            iSymmetricAlgorithm cipher = ((SymmetricType) model.get("symmetric")).getAlgorithm(algorithm).setSpec(spec).setInitVec(initVec);
            if (inputFile != null) {
                if (encrypt) {
                    byte[] cipherTxt = cipher.setKey(Base64.getEncoder().encode(key)).encrypt(inputText);
                    File file = Utils.createFile(inputFile.getParent() + "/encrypted_" + inputFile.getName());
                    Utils.writeFile(cipherTxt, file);
                    panel.getOutputBox().setFile(file);
                } else {
                    byte[] plainTxt = cipher.setKey(Base64.getEncoder().encode(key)).decrypt(inputText);
                    File file = Utils.createFile(inputFile.getParent() + "/decrypted_" + inputFile.getName());
                    Utils.writeFile(plainTxt, file);
                    panel.getOutputBox().setFile(file);
                }
            } else {
                if (encrypt) {
                    byte[] cipherTxt = cipher.setKey(key).encrypt(inputText);
                    panel.getOutputBox().setText(new String(cipherTxt, "utf-8"));
                } else {
                    byte[] plainTxt = cipher.setKey(key).decrypt(inputText);
                    panel.getOutputBox().setText(new String(plainTxt, "utf-8"));
                }
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void runAsymmetric(JPanel selectedPanel) {
        try {
            AsymmetricPanel panel = (AsymmetricPanel) selectedPanel;
            // set up info
            boolean encrypt = panel.getEncrypt().isSelected();
            String algorithm = (String) panel.getAlgorithmBox().getSelected();
            String mode = (String) panel.getModeBox().getSelected();
            String padding = (String) panel.getPaddingBox().getSelected();
            String spec = algorithm + "/" + mode + "/" + padding;
            // in out info
            byte[] inputText = panel.getInputBox().getText().getBytes("utf-8");
            byte[] key = panel.getKeyBox().getText().getBytes("utf-8");
            if (Arrays.equals(key, "".getBytes())) {
                throw new Exception("Empty key");
            }
            // process
            iAsymmetricAlgorithm cipher = ((AsymmetricType) model.get("asymmetric")).getAlgorithm(algorithm).setSpec(spec);
            if (encrypt) {
                byte[] cipherTxt = cipher.setPublicKey(key).encrypt(inputText);
                panel.getOutputBox().setText(new String(cipherTxt, "utf-8"));
            } else {
                byte[] plainTxt = cipher.setPrivateKey(key).decrypt(inputText);
                panel.getOutputBox().setText(new String(plainTxt));
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void runHash(JPanel selectedPanel) {
        try {
            HashPanel panel = (HashPanel) selectedPanel;
            // set up info
            String algorithm = (String) panel.getAlgorithmBox().getSelected();
            Integer mode = (Integer) panel.getTypeBox().getSelected();
            String spec = algorithm.trim() + mode;
            // in out info
            String inputText = panel.getInputBox().getText();
            // process
            iHashAlgorithm hashAlgorithm = ((HashType) model.get("hash")).getAlgorithm(algorithm).setSpec(spec);
            byte[] inputBytes = hashAlgorithm.encrypt(inputText.getBytes("utf-8"));
            panel.getOutputBox().setText(new String(Base64.getEncoder().encode(inputBytes), "utf-8"));
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void runPBE(JPanel selectedPanel) {
        try {
            PBEPanel panel = (PBEPanel) selectedPanel;
            // set up info
            String hashAlgorithm = (String) panel.getHashAlgorithmBox().getSelected();
            String symmetricAlgorithm = (String) panel.getSymmetricAlgorithmBox().getSelected();
            String spec = "PBEWith" + hashAlgorithm + "And" + symmetricAlgorithm;
            // in out info
            boolean encrypt = panel.getEncrypt().isSelected();
            char[] key = panel.getPasswordBox().getText().toCharArray();
            byte[] salt = panel.getSaltBox().getText().getBytes("utf-8");
            byte[] input = panel.getInputBox().getText().getBytes("utf-8");
            // process
            if (Arrays.equals(salt, "".getBytes())) {
                throw new Exception("Empty salt");
            }
            iPBEAlgorithm cipher = ((PBEType) model.get("pbe")).getAlgorithm(hashAlgorithm).setSpec(spec);
            if (encrypt) {
                byte[] cipherTxt = cipher.setKey(key).encrypt(input);
                panel.getOutputBox().setText(new String(cipherTxt, "utf-8"));
            } else {
                byte[] plainTxt = cipher.setKey(key).decrypt(input);
                panel.getOutputBox().setText(new String(plainTxt, "utf-8"));
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void handleException(Exception exception) {
        JOptionPane.showMessageDialog(null, exception.getMessage(), "Error!!!", JOptionPane.WARNING_MESSAGE);
        exception.printStackTrace();
    }

    // get
    public iAlgorithmType get(String type) {
        return model.get(type);
    }
}
