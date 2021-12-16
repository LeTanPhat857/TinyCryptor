package TinyCryptor.view.mainFrame.contentPanel;

import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JTabbedPane {
    // fields
    private SymmetricPanel symmetricPanel;
    private AsymmetricPanel asymmetricPanel;
    private HashPanel hashPanel;
    private PBEPanel pbePanel;

    // constructor
    public ContentPanel() {
        this.setBackground(Color.WHITE);
        symmetricPanel = new SymmetricPanel();
        asymmetricPanel = new AsymmetricPanel();
        hashPanel = new HashPanel();
        pbePanel = new PBEPanel();

        this.addTab("Symmetric", symmetricPanel);
        this.addTab("Asymmetric", asymmetricPanel);
        this.addTab("Hash", hashPanel);
        this.addTab("PBE", pbePanel);
    }

    // methods
}
