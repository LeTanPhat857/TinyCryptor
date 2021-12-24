package TinyCryptor.view;

import TinyCryptor.view.mainFrame.MainFrame;
import TinyCryptor.view.mainFrame.contentPanel.ContentPanel;

import javax.swing.*;
import java.awt.*;

public class View {
    // fields
    private MainFrame mainFrame;

    //constructor
    public View() {
        //
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("ComboBox.background", Color.WHITE);
        //
        this.mainFrame = new MainFrame();
    }

    public static View create() {
        return new View();
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
}
