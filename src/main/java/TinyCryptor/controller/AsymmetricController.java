package TinyCryptor.controller;

import TinyCryptor.model.AsymmetricType;
import TinyCryptor.model.Model;
import TinyCryptor.model.asymmetric.iAsymmetricAlgorithm;
import TinyCryptor.utils.Utils;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.subFrame.MessageFrame;
import TinyCryptor.view.subFrame.YesNoFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
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

    public void runAsymmetric(JPanel selectedPanel) throws Exception {
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
        File keyFile = panel.getKeyBox().getFile();
        File inputFile = panel.getInputBox().getFile();
        // check file
        if (keyFile != null) {
            key = Utils.readFile(keyFile);
        }
        if (inputFile != null) {
            inputText = Utils.readFile(inputFile);
        }
        if (Arrays.equals(key, "".getBytes())) {
            throw new Exception("Empty key");
        }
        // process
        iAsymmetricAlgorithm cipher = ((AsymmetricType) model.get("asymmetric")).getAlgorithm(algorithm).setSpec(spec);
        if (inputFile != null) {
            if (encrypt) {
                byte[] cipherTxt = cipher.setPublicKey(key).encrypt(inputText);
                File file = Utils.createFile(inputFile.getParent() + "/encrypted_" + inputFile.getName());
                Utils.writeFile(cipherTxt, file);
                panel.getOutputBox().setFile(file);
            } else {
                byte[] plainTxt = cipher.setPrivateKey(key).decrypt(inputText);
                File file = Utils.createFile(inputFile.getParent() + "/decrypted_" + inputFile.getName());
                Utils.writeFile(plainTxt, file);
                panel.getOutputBox().setFile(file);
            }
        } else {
            if (encrypt) {
                byte[] cipherTxt = cipher.setPublicKey(key).encrypt(inputText);
                panel.getOutputBox().setText(new String(cipherTxt, "utf-8"));
            } else {
                byte[] plainTxt = cipher.setPrivateKey(key).decrypt(inputText);
                panel.getOutputBox().setText(new String(plainTxt, "utf-8"));
            }
        }
        MessageFrame.create("Notification", "Run successfully!").setVisible(true);
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

                boolean bool = YesNoFrame.create("Exit", "Do you want to export key file?").setVisible(true).getBool();
                if (bool) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Export public key and private key");
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files (.txt)", "txt"));
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Key Files (.key)", "key"));
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images Files (.png .jpg)", "jpg", "png"));
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents (.pdf)", "pdf"));
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office Documents (.docx .xlsx .pptx)", "docx", "xlsx", "pptx"));

                    int returnVal = fileChooser.showSaveDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        File pubFile = Utils.createFile(selectedFile.getPath() + "_" + asymmetricPanel.getKeySizeBox().getSelected() + ".publicKey");
                        File priFile = Utils.createFile(selectedFile.getPath() + "_" + asymmetricPanel.getKeySizeBox().getSelected() + ".privateKey");
                        Utils.writeFile(Base64.getEncoder().encode(algorithm.getPublicKey()), pubFile);
                        Utils.writeFile(Base64.getEncoder().encode(algorithm.getPrivateKey()), priFile);
                    }
                }
                MessageFrame.create("Notification", "Create key successfully!").setVisible(true);
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
