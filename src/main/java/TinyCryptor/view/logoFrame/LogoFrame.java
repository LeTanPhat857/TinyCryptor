package TinyCryptor.view.logoFrame;

import TinyCryptor.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class LogoFrame extends JFrame {

    // fields
    private static LogoFrame instance;

    // constructor
    public LogoFrame() {
        JLabel label = new JLabel(Utils.getImageIcon("images/icon/logo.png", 200, 250, Image.SCALE_SMOOTH));

        this.setUndecorated(true); // Remove title bar
        this.setBackground(new Color(0, 0, 0, 0));
        this.add(label);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    // methods
    public static LogoFrame getInstance() {
        if (instance == null) {
            return instance = new LogoFrame();
        }
        return instance;
    }

    public static void showLogo() {
        getInstance().setVisible(true);
    }

    public static void disposeLogo() {
        getInstance().dispose();
    }
}
