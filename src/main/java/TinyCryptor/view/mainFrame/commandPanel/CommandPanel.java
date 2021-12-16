package TinyCryptor.view.mainFrame.commandPanel;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.mainFrame.contentPanel.helperPanel.RoundedButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CommandPanel extends JPanel {

    // fields
    private JButton infoBtn;
    private JButton runBtn;
    private JButton helpBtn;
    private JButton exitBtn;

    // constructor
    public CommandPanel() {
        // set up
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.blue), new EmptyBorder(5, 11, 5, 17)));
        // create button
        this.infoBtn = new RoundedButton();
        this.runBtn = new RoundedButton();
        this.helpBtn = new RoundedButton();
        this.exitBtn = new RoundedButton();
        // create tempPanel for runBtn and exitBtn
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setLayout(new GridLayout(1, 2, 30, 10));

        infoBtn.setIcon(Utils.getImageIcon("images/icon/info.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        runBtn.setIcon(Utils.getImageIcon("images/icon/run.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        helpBtn.setIcon(Utils.getImageIcon("images/icon/help.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        exitBtn.setIcon(Utils.getImageIcon("images/icon/exit.png", 16, 16, Image.SCALE_AREA_AVERAGING));

        infoBtn.setToolTipText("about TinyCryptor");
        runBtn.setToolTipText("run");
        helpBtn.setToolTipText("help");
        exitBtn.setToolTipText("exit");

        tempPanel.add(runBtn);
        tempPanel.add(helpBtn);
        tempPanel.add(exitBtn);
        // add button
        this.add(infoBtn, BorderLayout.WEST);
        this.add(tempPanel, BorderLayout.EAST);
    }

    // methods
    public JButton getInfoBtn() {
        return this.infoBtn;
    }

    public JButton getRunBtn() {
        return this.runBtn;
    }

    public JButton getHelpBtn() {
        return helpBtn;
    }

    public JButton getExitBtn() {
        return this.exitBtn;
    }
}
