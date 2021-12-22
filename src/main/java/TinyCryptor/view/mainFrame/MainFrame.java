package TinyCryptor.view.mainFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.mainFrame.commandPanel.CommandPanel;
import TinyCryptor.view.mainFrame.contentPanel.ContentPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // fields
    private ContentPanel contentPanel;
    private CommandPanel commandPanel;

    // constructor
    public MainFrame() {
        // set up
        this.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));
        this.setTitle("TinyCryptor");
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        // create subPanel
        contentPanel = new ContentPanel();
        commandPanel = new CommandPanel();
        // add subPanel
        this.add(contentPanel, BorderLayout.CENTER);
        this.add(commandPanel, BorderLayout.SOUTH);
        // set up
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // methods
    public JButton getInfoBtn() {
        return commandPanel.getInfoBtn();
    }

    public JButton getRunBtn() {
        return this.commandPanel.getRunBtn();
    }

    public JButton getHelpBtn() {
        return this.commandPanel.getHelpBtn();
    }

    public JButton getExitBtn() {
        return commandPanel.getExitBtn();
    }

    //
    public ContentPanel getContentPanel() {
        return contentPanel;
    }
}
