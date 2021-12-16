package TinyCryptor.view.mainFrame.contentPanel.helperPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    public JComboBox getComboBox() {
        return box;
    }

    public SetUpBoxPanel hideBox() {
        this.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(5, 5, 5, 5), "", 1, 1));
        box.setVisible(false);
        return this;
    }
}
