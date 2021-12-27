package TinyCryptor.view.mainFrame.contentPanel.hashPanel;

import TinyCryptor.view.helper.InOutBoxPanel;
import TinyCryptor.view.helper.SetUpBoxPanel;

import javax.swing.*;
import java.awt.*;

public class HashPanel extends JPanel {
    // fields
    private InOutPanel inOutPanel;
    private SetUpPanel setUpPanel;

    // constructor
    public HashPanel() {
        this.setName("hash");
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        inOutPanel = new InOutPanel();
        setUpPanel = new SetUpPanel();

        this.add(inOutPanel, BorderLayout.CENTER);
        this.add(setUpPanel, BorderLayout.EAST);
    }

    // methods
    // in out panel
    public InOutBoxPanel getInputBox() {
        return inOutPanel.getPlainBox();
    }

    public InOutBoxPanel getOutputBox() {
        return inOutPanel.getCipherBox();
    }

    // set up panel
    public SetUpBoxPanel getAlgorithmBox() {
        return setUpPanel.getAlgorithmBox();
    }

    public SetUpBoxPanel getTypeBox() {
        return setUpPanel.getTypeBox();
    }
}
