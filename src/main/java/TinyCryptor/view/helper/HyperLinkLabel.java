package TinyCryptor.view.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HyperLinkLabel extends JLabel {
    // fields
    private String url;
    private String html = "<html><a href=''>%s</a></html>";

    // constructor
    public HyperLinkLabel(String text) {
        this(text, null, null);
    }

    public HyperLinkLabel(String text, String url) {
        this(text, url, null);
    }

    public HyperLinkLabel(String text, String url, String tooltip) {
        super(text);
        this.url = url;

        setForeground(Color.BLUE.darker());
        setToolTipText(tooltip);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setText(String.format(html, text));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setText(text);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(HyperLinkLabel.this.url));
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(HyperLinkLabel.this,
                            "Could not open the hyperlink. Error: " + e1.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    // methods
    public void setURL(String url) {
        this.url = url;
    }
}
