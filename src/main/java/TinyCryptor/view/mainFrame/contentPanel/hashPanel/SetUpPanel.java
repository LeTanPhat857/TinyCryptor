package TinyCryptor.view.mainFrame.contentPanel.hashPanel;

import TinyCryptor.view.mainFrame.contentPanel.helperPanel.SetUpBoxPanel;

import javax.swing.*;
import java.awt.*;

public class SetUpPanel extends JPanel {
    // fields
    private SetUpBoxPanel algorithmSetUpBoxPanel;
    private SetUpBoxPanel modeSetUpBoxPanel;

    // constructor
    public SetUpPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Set up"), BorderFactory.createEmptyBorder(25, 0, 222, 0)));

        // create component
        algorithmSetUpBoxPanel = new SetUpBoxPanel("Algorithm");
        modeSetUpBoxPanel = new SetUpBoxPanel("Type");

        // add component
        this.add(algorithmSetUpBoxPanel);
        this.add(modeSetUpBoxPanel);
    }

    // methods
    // public get
    public SetUpBoxPanel getAlgorithmBox() {
        return this.algorithmSetUpBoxPanel;
    }

    public SetUpBoxPanel getTypeBox() {
        return modeSetUpBoxPanel;
    }
}