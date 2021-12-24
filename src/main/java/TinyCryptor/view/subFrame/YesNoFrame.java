package TinyCryptor.view.subFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class YesNoFrame {

    // fields
    private JDialog dialog;
    private boolean bool;

    // constructor
    private YesNoFrame(String title, String content) {
        JButton yesBtn = new RoundedButton();
        JButton noBtn = new RoundedButton();
        yesBtn.setText("Yes");
        noBtn.setText("No");
        Object[] options = {yesBtn, new JLabel("  "), noBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new JLabel(content, JLabel.CENTER));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        dialog = optionPane.createDialog(title);
        dialog.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));

        yesBtn.addActionListener(e -> {
            dialog.dispose();
            bool = true;
        });

        noBtn.addActionListener(e -> {
            dialog.dispose();
            bool = false;
        });
    }

    // methods
    public static YesNoFrame create(String title, String content) {
        return new YesNoFrame(title, content);
    }

    public YesNoFrame setVisible(boolean bool) {
        dialog.setVisible(bool);
        return this;
    }

    public boolean getBool() {
        return bool;
    }
}
