package TinyCryptor.view.mainFrame.contentPanel.hashPanel;

import TinyCryptor.view.helper.SetUpBoxPanel;

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
        algorithmSetUpBoxPanel = new SetUpBoxPanel("Algorithm").setToolTip("choose algorithm");
        modeSetUpBoxPanel = new SetUpBoxPanel("Type").setToolTip("choose type");

        algorithmSetUpBoxPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        modeSetUpBoxPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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