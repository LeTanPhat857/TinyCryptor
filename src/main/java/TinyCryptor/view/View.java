package TinyCryptor.view;

import TinyCryptor.controller.Controller;
import TinyCryptor.model.AsymmetricType;
import TinyCryptor.model.HashType;
import TinyCryptor.model.PBE.iPBEAlgorithm;
import TinyCryptor.model.PBEType;
import TinyCryptor.model.SymmetricType;
import TinyCryptor.model.asymmetric.iAsymmetricAlgorithm;
import TinyCryptor.model.hash.iHashAlgorithm;
import TinyCryptor.model.symmetric.iSymmetricAlgorithm;
import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.RoundedButton;
import TinyCryptor.view.mainFrame.MainFrame;
import TinyCryptor.view.mainFrame.contentPanel.ContentPanel;
import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Base64;

public class View {
    // fields
    private MainFrame mainFrame;
    private Controller controller;

    //constructor
    public View(Controller controller) {
        //
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("ComboBox.background", Color.WHITE);
        //
        this.mainFrame = new MainFrame();
        this.controller = controller;
        //
        this.drawContentPanel();
    }

    // methods
    public void setVisible(boolean visible) {
        mainFrame.setVisible(visible);
    }

    // command panel
    public JButton getInfoBtn() {
        return mainFrame.getInfoBtn();
    }

    public JButton getRunBtn() {
        return mainFrame.getRunBtn();
    }

    public JButton getHelpBtn() {
        return mainFrame.getHelpBtn();
    }

    public JButton getExitBtn() {
        return mainFrame.getExitBtn();
    }

    // contend panel
    public ContentPanel getContentPanel() {
        return mainFrame.getContentPanel();
    }

    // draw panel
    private void drawContentPanel() {
        Component[] components = getContentPanel().getComponents();
        for (Component component : components) {
            drawTabbedPanel((JPanel) component);
        }
    }

    private void drawTabbedPanel(JPanel panel) {
        switch (panel.getName()) {
            case "symmetric": {
                drawSymmetricPanel((SymmetricPanel) panel);
                break;
            }
            case "asymmetric": {
                drawAsymmetricPanel((AsymmetricPanel) panel);
                break;
            }
            case "hash": {
                drawHashPanel((HashPanel) panel);
                break;
            }
            case "pbe": {
                drawPBEPanel((PBEPanel) panel);
                break;
            }
        }
    }

    private void drawSymmetricPanel(SymmetricPanel symmetricPanel) {
        SymmetricType symmetricType = (SymmetricType) controller.get("symmetric");
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
                handleNotification("Create key successfully!");
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

    private void drawAsymmetricPanel(AsymmetricPanel asymmetricPanel) {
        AsymmetricType asymmetricType = (AsymmetricType) controller.get("asymmetric");
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
                handleNotification("Create key successfully!");
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

    private void drawHashPanel(HashPanel hashPanel) {
        HashType symmetricType = (HashType) controller.get("hash");
        iHashAlgorithm defaultAlgorithm = symmetricType.getAlgorithm(0);
        // set up panel
        hashPanel.getAlgorithmBox().setItemList(symmetricType.getAlgorithmList());
        hashPanel.getTypeBox().setItemList(defaultAlgorithm.getTypeList());
        // action for algorithm box (set up panel)
        hashPanel.getAlgorithmBox().getComboBox().addActionListener(e -> {
            String algorithmName = (String) hashPanel.getAlgorithmBox().getSelected();
            iHashAlgorithm algorithm = symmetricType.getAlgorithm(algorithmName);

            hashPanel.getTypeBox().setItemList(algorithm.getTypeList());
        });
    }

    private void drawPBEPanel(PBEPanel pbePanel) {
        PBEType pbeType = (PBEType) controller.get("pbe");
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
            handleNotification("Create salt successfully!");
        });

        pbePanel.getShowSaltBtn().addActionListener(e -> {
            String algorithmName = (String) pbePanel.getHashAlgorithmBox().getSelected();
            iPBEAlgorithm algorithm = pbeType.getAlgorithm(algorithmName);

            pbePanel.getSaltBox().setText(Base64.getEncoder().encodeToString(algorithm.getSalt()));
        });
    }

    private void handleNotification(String massage) {
        JButton okBtn = new RoundedButton();
        okBtn.setText("Ok");
        Object[] options = {okBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new JLabel(massage, JLabel.CENTER));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        Dialog dialog = optionPane.createDialog("Notification");
        dialog.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));

        okBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }
}
