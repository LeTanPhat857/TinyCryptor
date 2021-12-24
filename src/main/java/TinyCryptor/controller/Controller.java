package TinyCryptor.controller;

import TinyCryptor.model.*;
import TinyCryptor.model.PBE.iPBEAlgorithm;
import TinyCryptor.model.asymmetric.iAsymmetricAlgorithm;
import TinyCryptor.model.hash.iHashAlgorithm;
import TinyCryptor.model.symmetric.iSymmetricAlgorithm;
import TinyCryptor.utils.Utils;
import TinyCryptor.view.View;
import TinyCryptor.view.helpFrame.HelpFrame;
import TinyCryptor.view.helper.RoundedButton;
import TinyCryptor.view.infoFrame.InfoFrame;
import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Base64;

public class Controller {
    // fields
    private static Controller instance;

    private Model model;
    private View view;

    private SymmetricController symmContr;
    private AsymmetricController asymmContr;
    private HashController hashContr;
    private PBEController pbeContr;

    // constructor
    private Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.symmContr = SymmetricController.create(model, view);
        this.asymmContr = AsymmetricController.create(model, view);
        this.hashContr = HashController.create(model, view);
        this.pbeContr = PBEController.create(model, view);
    }

    // methods
    public static Controller create(Model model, View view) {
        return instance = new Controller(model, view);
    }

    public static Controller getInstance() {
        if (instance != null) {
            return instance;
        }
        return create(null, null);
    }

    public void init() {
        // draw
        drawContentPanel();
        // command
        view.getInfoBtn().addActionListener(e -> info());
        view.getRunBtn().addActionListener(e -> run());
        view.getHelpBtn().addActionListener(e -> help());
        view.getExitBtn().addActionListener(e -> exit());
        // show view
        view.setVisible(true);
    }

    // action handler
    public void info() {
        InfoFrame.createInfoFrame().init();
    }

    public void help() {
        JPanel selectedPanel = (JPanel) view.getContentPanel().getSelectedComponent();
        HelpFrame.createHelpFrame().open(selectedPanel.getName()).init();
    }

    public void exit() {
        JButton yesBtn = new RoundedButton();
        JButton noBtn = new RoundedButton();
        yesBtn.setText("Yes");
        noBtn.setText("No");
        Object[] options = {yesBtn, new JLabel("  "), noBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new JLabel("Do you want to exit?", JLabel.CENTER));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        Dialog dialog = optionPane.createDialog("Exit");
        dialog.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));

        yesBtn.addActionListener(a -> System.exit(0));
        noBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public void run() {
        JPanel selectedPanel = (JPanel) view.getContentPanel().getSelectedComponent();
        switch (selectedPanel.getName()) {
            case "symmetric": {
                symmContr.runSymmetric(selectedPanel);
                break;
            }
            case "asymmetric": {
                asymmContr.runAsymmetric(selectedPanel);
                break;
            }
            case "hash": {
                hashContr.runHash(selectedPanel);
                break;
            }
            case "pbe": {
                pbeContr.runPBE(selectedPanel);
                break;
            }
        }
    }

    public void handleException(Exception exception) {
        exception.printStackTrace();

        JButton okBtn = new RoundedButton();
        okBtn.setText("Ok");
        Object[] options = {okBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new JLabel(exception.getMessage(), JLabel.CENTER));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        Dialog dialog = optionPane.createDialog("Error !!!");
        dialog.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));

        okBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // draw panel
    private void drawContentPanel() {
        Component[] components = view.getContentPanel().getComponents();
        for (Component component : components) {
            drawTabbedPanel((JPanel) component);
        }
    }

    private void drawTabbedPanel(JPanel panel) {
        switch (panel.getName()) {
            case "symmetric": {
                symmContr.drawSymmetricPanel((SymmetricPanel) panel);
                break;
            }
            case "asymmetric": {
                asymmContr.drawAsymmetricPanel((AsymmetricPanel) panel);
                break;
            }
            case "hash": {
                hashContr.drawHashPanel((HashPanel) panel);
                break;
            }
            case "pbe": {
                pbeContr.drawPBEPanel((PBEPanel) panel);
                break;
            }
        }
    }

    public void notify(String massage) {
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
