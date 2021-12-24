package TinyCryptor.view.subFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class MessageFrame {

    // fields
    private JDialog dialog;

    // constructor
    private MessageFrame(String title, String content) {
        JButton okBtn = new RoundedButton();
        okBtn.setText("Ok");
        Object[] options = {okBtn};

        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(new JLabel(content, JLabel.CENTER));
        optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
        optionPane.setOptions(options);

        dialog = optionPane.createDialog(title);
        dialog.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));

        okBtn.addActionListener(e -> dialog.dispose());
    }

    // methods
    public static MessageFrame create(String title, String content) {
        return new MessageFrame(title, content);
    }

    public void setVisible(boolean bool) {
        dialog.setVisible(bool);
    }

}
