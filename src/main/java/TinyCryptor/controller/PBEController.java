package TinyCryptor.controller;

import TinyCryptor.model.Model;
import TinyCryptor.model.PBE.iPBEAlgorithm;
import TinyCryptor.model.PBEType;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.Base64;

public class PBEController {
    // fields
    private Model model;
    private View view;

    // constructor
    private PBEController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // methods
    public static PBEController create(Model model, View view) {
        return new PBEController(model, view);
    }

    public void runPBE(JPanel selectedPanel) {
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
            Controller.getInstance().handleException(e);
        }
    }

    public void drawPBEPanel(PBEPanel pbePanel) {
        PBEType pbeType = (PBEType) model.get("pbe");
        iPBEAlgorithm defaultAlgorithm = pbeType.getAlgorithm(0);
        // set up panel
        pbePanel.getHashAlgorithmBox().setItemList(pbeType.getAlgorithmList());
        pbePanel.getSymmetricAlgorithmBox().setItemList(defaultAlgorithm.getSymmetricAlgorithmList());
        // action for algorithm box (set up panel)
        pbePanel.getHashAlgorithmBox().getComboBox().addActionListener(e -> {
            String algorithmName = (String) pbePanel.getHashAlgorithmBox().getSelected();
            iPBEAlgorithm algorithm = pbeType.getAlgorithm(algorithmName);

            pbePanel.getSymmetricAlgorithmBox().setItemList(algorithm.getSymmetricAlgorithmList());
        });

        pbePanel.getCreateSaltBtn().addActionListener(e -> {
            String algorithmName = (String) pbePanel.getHashAlgorithmBox().getSelected();
            iPBEAlgorithm algorithm = pbeType.getAlgorithm(algorithmName);

            algorithm.generateRandomSalt();
            Controller.getInstance().notify("Create salt successfully!");
        });

        pbePanel.getShowSaltBtn().addActionListener(e -> {
            String algorithmName = (String) pbePanel.getHashAlgorithmBox().getSelected();
            iPBEAlgorithm algorithm = pbeType.getAlgorithm(algorithmName);

            pbePanel.getSaltBox().setText(Base64.getEncoder().encodeToString(algorithm.getSalt()));
        });
    }
}
