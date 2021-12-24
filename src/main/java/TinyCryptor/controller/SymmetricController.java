package TinyCryptor.controller;

import TinyCryptor.model.Model;
import TinyCryptor.model.SymmetricType;
import TinyCryptor.model.symmetric.iSymmetricAlgorithm;
import TinyCryptor.utils.Utils;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;
import TinyCryptor.view.subFrame.MessageFrame;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.Base64;

public class SymmetricController {
    // fields
    private Model model;
    private View view;

    // constructor
    private SymmetricController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // methods
    public static SymmetricController create(Model model, View view) {
        return new SymmetricController(model, view);
    }

    public void runSymmetric(JPanel selectedPanel) throws Exception {
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
    }

    public void drawSymmetricPanel(SymmetricPanel symmetricPanel) {
        SymmetricType symmetricType = (SymmetricType) model.get("symmetric");
        iSymmetricAlgorithm defaultAlgorithm = symmetricType.getAlgorithm(0);
        // set up panel
        symmetricPanel.getAlgorithmBox().setItemList(symmetricType.getAlgorithmList());
        symmetricPanel.getKeySizeBox().setItemList(defaultAlgorithm.getKeyList());
        symmetricPanel.getModeBox().setItemList(defaultAlgorithm.getModeList());
        symmetricPanel.getPaddingBox().setItemList(defaultAlgorithm.getPaddingList());
        // action for algorithm box (set up panel)
        symmetricPanel.getAlgorithmBox().getComboBox().addActionListener(e -> {
            String algorithmName = (String) symmetricPanel.getAlgorithmBox().getSelected();
            iSymmetricAlgorithm algorithm = symmetricType.getAlgorithm(algorithmName);

            symmetricPanel.getKeySizeBox().setItemList(algorithm.getKeyList());
            symmetricPanel.getModeBox().setItemList(algorithm.getModeList());
            symmetricPanel.getPaddingBox().setItemList(algorithm.getPaddingList());
        });
        // action for new key button (set up panel)
        symmetricPanel.getNewKeyBtn().addActionListener(e -> {
            try {
                String algorithmName = (String) symmetricPanel.getAlgorithmBox().getSelected();
                iSymmetricAlgorithm algorithm = symmetricType.getAlgorithm(algorithmName);

                algorithm.generateKey((int) symmetricPanel.getKeySizeBox().getSelected());
                symmetricPanel.getKeyBox().setText("");
                MessageFrame.create("Notification", "Create key successfully!").setVisible(true);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        // action for show key button (set up panel)
        symmetricPanel.getShowKeyBtn().addActionListener(e -> {
            String algorithmName = (String) symmetricPanel.getAlgorithmBox().getSelected();
            iSymmetricAlgorithm algorithm = symmetricType.getAlgorithm(algorithmName);

            symmetricPanel.getKeyBox().setText(new String(algorithm.getKey()));
        });
    }
}
