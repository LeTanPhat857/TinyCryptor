package TinyCryptor.controller;

import TinyCryptor.model.AsymmetricType;
import TinyCryptor.model.Model;
import TinyCryptor.model.asymmetric.iAsymmetricAlgorithm;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.Base64;

public class AsymmetricController {
    // fields
    private Model model;
    private View view;

    // constructor
    private AsymmetricController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // methods
    public static AsymmetricController create(Model model, View view) {
        return new AsymmetricController(model, view);
    }

    public void runAsymmetric(JPanel selectedPanel) {
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
            Controller.getInstance().handleException(e);
        }
    }

    public void drawAsymmetricPanel(AsymmetricPanel asymmetricPanel) {
        AsymmetricType asymmetricType = (AsymmetricType) model.get("asymmetric");
        iAsymmetricAlgorithm defaultAlgorithm = asymmetricType.getAlgorithm(0);
        // set up panel
        asymmetricPanel.getAlgorithmBox().setItemList(asymmetricType.getAlgorithmList());
        asymmetricPanel.getKeySizeBox().setItemList(defaultAlgorithm.getKeyList());
        asymmetricPanel.getModeBox().setItemList(defaultAlgorithm.getModeList());
        asymmetricPanel.getPaddingBox().setItemList(defaultAlgorithm.getPaddingList());
        // action for algorithm box (set up panel)
        asymmetricPanel.getAlgorithmBox().getComboBox().addActionListener(e -> {
            String algorithmName = (String) asymmetricPanel.getAlgorithmBox().getSelected();
            iAsymmetricAlgorithm algorithm = asymmetricType.getAlgorithm(algorithmName);

            asymmetricPanel.getKeySizeBox().setItemList(algorithm.getKeyList());
            asymmetricPanel.getModeBox().setItemList(algorithm.getModeList());
            asymmetricPanel.getPaddingBox().setItemList(algorithm.getPaddingList());
        });
        // action for new key button (set up panel)
        asymmetricPanel.getNewKeyBtn().addActionListener(e -> {
            try {
                String algorithmName = (String) asymmetricPanel.getAlgorithmBox().getSelected();
                iAsymmetricAlgorithm algorithm = asymmetricType.getAlgorithm(algorithmName);

                algorithm.generateKey((int) asymmetricPanel.getKeySizeBox().getSelected());
                asymmetricPanel.getKeyBox().setText("");
                Controller.getInstance().notify("Create key successfully!");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        // action for public key button (set up panel)
        asymmetricPanel.getPublicKeyBtn().addActionListener(e -> {
            String algorithmName = (String) asymmetricPanel.getAlgorithmBox().getSelected();
            iAsymmetricAlgorithm algorithm = asymmetricType.getAlgorithm(algorithmName);

            asymmetricPanel.getKeyBox().setText(Base64.getEncoder().encodeToString(algorithm.getPublicKey()));
        });
        // action for private key button (set up panel)
        asymmetricPanel.getPrivateKeyBtn().addActionListener(e -> {
            String algorithmName = (String) asymmetricPanel.getAlgorithmBox().getSelected();
            iAsymmetricAlgorithm algorithm = asymmetricType.getAlgorithm(algorithmName);

            asymmetricPanel.getKeyBox().setText(Base64.getEncoder().encodeToString(algorithm.getPrivateKey()));
        });
    }
}
