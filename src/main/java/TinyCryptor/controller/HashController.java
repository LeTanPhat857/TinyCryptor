package TinyCryptor.controller;

import TinyCryptor.model.HashType;
import TinyCryptor.model.Model;
import TinyCryptor.model.hash.iHashAlgorithm;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;

import javax.swing.*;
import java.util.Base64;

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
        String inputText = panel.getInputBox().getText();
        // process
        iHashAlgorithm hashAlgorithm = ((HashType) model.get("hash")).getAlgorithm(algorithm).setSpec(spec);
        byte[] inputBytes = hashAlgorithm.encrypt(inputText.getBytes("utf-8"));
        panel.getOutputBox().setText(new String(Base64.getEncoder().encode(inputBytes), "utf-8"));
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
