package TinyCryptor.controller;

import TinyCryptor.model.HashType;
import TinyCryptor.model.Model;
import TinyCryptor.model.hash.iHashAlgorithm;
import TinyCryptor.utils.Utils;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.subFrame.MessageFrame;

import javax.swing.*;
import java.io.File;

public class HashController {
    // fields
    private Model model;
    private View view;

    // constructor
    private HashController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // methods
    public static HashController create(Model model, View view) {
        return new HashController(model, view);
    }

    public void runHash(JPanel selectedPanel) throws Exception {
        HashPanel panel = (HashPanel) selectedPanel;
        // set up info
        String algorithm = (String) panel.getAlgorithmBox().getSelected();
        String mode = (String) panel.getTypeBox().getSelected();
        String spec = algorithm.trim() + mode;
        // in out info
        byte[] inputText = panel.getInputBox().getText().getBytes("utf-8");
        File inputFile = panel.getInputBox().getFile();
        if (inputFile != null) {
            inputText = Utils.readFile(inputFile);
        }
        // process
        iHashAlgorithm hashAlgorithm = ((HashType) model.get("hash")).getAlgorithm(algorithm).setSpec(spec);
        byte[] cipherTxt = hashAlgorithm.encrypt(inputText);
        panel.getOutputBox().setText(Utils.convertStringToHex(new String(cipherTxt, "utf-8")));
    }

    public void drawHashPanel(HashPanel hashPanel) {
        HashType hashType = (HashType) model.get("hash");
        iHashAlgorithm defaultAlgorithm = hashType.getAlgorithm(0);
        // set up panel
        hashPanel.getAlgorithmBox().setItemList(hashType.getAlgorithmList());
        hashPanel.getTypeBox().setItemList(defaultAlgorithm.getTypeList());
        // action for algorithm box (set up panel)
        hashPanel.getAlgorithmBox().getComboBox().addActionListener(e -> {
            String algorithmName = (String) hashPanel.getAlgorithmBox().getSelected();
            iHashAlgorithm algorithm = hashType.getAlgorithm(algorithmName);

            hashPanel.getTypeBox().setItemList(algorithm.getTypeList());
        });
    }
}
