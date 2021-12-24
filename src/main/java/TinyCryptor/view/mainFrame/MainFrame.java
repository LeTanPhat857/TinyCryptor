package TinyCryptor.view.mainFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.mainFrame.commandPanel.CommandPanel;
import TinyCryptor.view.mainFrame.contentPanel.ContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        this.setMinimumSize(new Dimension(this.getWidth(), this.getHeight()));

        this.customizeResize();
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

    private void customizeResize() {
        this.getContentPanel().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension dim = getContentPanel().getSize();
                Dimension minDim = getContentPanel().getMinimumSize();
                if (dim.width < minDim.width)
                    dim.width = minDim.width;
                if (dim.height < minDim.height)
                    dim.height = minDim.height;
                getContentPanel().setSize(dim);
            }
        });
    }

}
