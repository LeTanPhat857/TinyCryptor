package TinyCryptor.view.subFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class ExitFrame {

    // fields
    private JDialog dialog;

    // constructor
    private ExitFrame() {
        JButton yesBtn = new RoundedButton();
        JButton noBtn = new RoundedButton();
        yesBtn.setText("Yes");
        noBtn.setText("No");
        Object[] options = {yesBtn, new JLabel("  "), noBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new JLabel("Do you want to exit?", JLabel.CENTER));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        dialog = optionPane.createDialog("Exit");
        dialog.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));

        yesBtn.addActionListener(a -> System.exit(0));
        noBtn.addActionListener(e -> dialog.dispose());
    }

    // methods
    public static ExitFrame create() {
        return new ExitFrame();
    }

    public void setVisible(boolean bool) {
        dialog.setVisible(bool);
    }

}
