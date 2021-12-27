package TinyCryptor.view.helper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.util.List;

public class SetUpBoxPanel<T> extends JPanel {
    // fields
    JComboBox<T> box;

    // constructor
    public SetUpBoxPanel(String title) {
        // set up
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(5, 5, 5, 5), title, 1, 1));
        // create component
        box = new JComboBox<>();
        // add component
        this.add(box, BorderLayout.NORTH);
    }

    // methods
    public void setItemList(List items) {
        box.removeAllItems();
        for (int i = 0; i < items.size(); i++) {
            box.addItem((T) items.get(i));
        }
    }

    public T getSelected() {
        return (T) box.getSelectedItem();
    }

    public SetUpBoxPanel setToolTip(String toolTip) {
        box.setToolTipText(toolTip);
        return this;
    }

    public JComboBox getComboBox() {
        return box;
    }
}
