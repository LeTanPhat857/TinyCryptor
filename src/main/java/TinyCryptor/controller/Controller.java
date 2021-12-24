package TinyCryptor.controller;

import TinyCryptor.model.Model;
import TinyCryptor.view.View;
import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;
import TinyCryptor.view.subFrame.YesNoFrame;
import TinyCryptor.view.subFrame.HelpFrame;
import TinyCryptor.view.subFrame.InfoFrame;
import TinyCryptor.view.subFrame.MessageFrame;

import javax.swing.*;
import java.awt.*;

public class Controller {
    // fields
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
        return new Controller(model, view);
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
        InfoFrame.create().setVisible(true);
    }

    public void help() {
        JPanel selectedPanel = (JPanel) view.getContentPanel().getSelectedComponent();
        HelpFrame.create().open(selectedPanel.getName()).setVisible(true);
    }

    public void exit() {
        boolean bool = YesNoFrame.create("Exit", "Do you want to exit?").setVisible(true).getBool();
        if (bool) {
            System.exit(0);
        }
    }

    public void run() {
        JPanel selectedPanel = (JPanel) view.getContentPanel().getSelectedComponent();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            MessageFrame.create("ERROR !!!", e.getMessage()).setVisible(true);
        }
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
}
