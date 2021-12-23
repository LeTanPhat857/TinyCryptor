package TinyCryptor.view.mainFrame.contentPanel;

import TinyCryptor.view.mainFrame.contentPanel.PBEPanel.PBEPanel;
import TinyCryptor.view.mainFrame.contentPanel.asymmetricPanel.AsymmetricPanel;
import TinyCryptor.view.mainFrame.contentPanel.hashPanel.HashPanel;
import TinyCryptor.view.mainFrame.contentPanel.symmetricPanel.SymmetricPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

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

        this.setToolTipTextAt(0, "symmetric type");
        this.setToolTipTextAt(1, "asymmetric type");
        this.setToolTipTextAt(2, "hash type");
        this.setToolTipTextAt(3, "PBE type");

        this.addMouseMotionListener(listener);
    }

    // methods
    MouseMotionListener listener = new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            if (findTabPaneIndex(e.getPoint(), tabbedPane) > -1) {
                tabbedPane.setCursor(new Cursor((Cursor.HAND_CURSOR)));
            } else {
                tabbedPane.setCursor(new Cursor((Cursor.DEFAULT_CURSOR)));
            }
        }
    };

    private static int findTabPaneIndex(Point p, JTabbedPane tabbedPane) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (tabbedPane.getBoundsAt(i).contains(p.x, p.y)) {
                return i;
            }
        }
        return -1;
    }
}
