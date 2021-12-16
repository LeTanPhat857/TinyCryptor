package TinyCryptor.view.mainFrame.contentPanel.helperPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {

    int acrWidth = 10;
    int arcHeight = 10;

    public RoundedButton() {
        super();
        setBackground(Color.WHITE);
        setFocusable(false);
        setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) {
                    setBackground(Color.LIGHT_GRAY);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.WHITE);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getSize().width, getSize().height, acrWidth, arcHeight);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, acrWidth, arcHeight);
    }

    // Hit detection.
    Shape shape;
    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), acrWidth, arcHeight);
        }
        return shape.contains(x, y);
    }
}
