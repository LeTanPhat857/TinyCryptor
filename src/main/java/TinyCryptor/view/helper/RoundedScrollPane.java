package TinyCryptor.view.helper;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedScrollPane extends JScrollPane {
    // fields
    int acrWidth = 10;
    int arcHeight = 10;

    // constructor
    public RoundedScrollPane() {
        super();
        this.setBackground(Color.WHITE);
    }

    public RoundedScrollPane(Component component) {
        super(component, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.scrollBarWidth = 8;
                this.thumbColor = Color.GRAY;
                this.trackColor = Color.WHITE;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
//        setOpaque(false);
//        setFocusable(false);
    }

    // methods
    protected void paintComponent(Graphics g) {
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
