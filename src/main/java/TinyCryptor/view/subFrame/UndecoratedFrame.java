package TinyCryptor.view.subFrame;

import TinyCryptor.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class UndecoratedFrame extends JDialog {

    // fields

    // constructor
    private UndecoratedFrame(JLabel label) {
        this.setModal(true);
        this.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.add(label);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    // methods
    public static UndecoratedFrame createLogoFrame() {
        JLabel label = new JLabel(Utils.getImageIcon("images/icon/logo.png", 200, 250, Image.SCALE_SMOOTH));
        return new UndecoratedFrame(label);
    }

    public static UndecoratedFrame createProcessingFrame() {
        JLabel label = new JLabel(new ImageIcon(Objects.requireNonNull(Utils.getImage("images/icon/processing.gif"))));
        return new UndecoratedFrame(label);
    }

    public UndecoratedFrame setShow(boolean bool) {
        this.setVisible(bool);
        return this;
    }

    public void disposeFrame() {
        this.dispose();
    }

}
