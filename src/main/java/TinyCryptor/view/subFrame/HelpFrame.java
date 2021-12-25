package TinyCryptor.view.subFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HelpFrame extends JFrame {

    // fields
    private JLabel label;

    // constructor
    private HelpFrame() {
        this.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));
        this.setTitle("Help");
        this.setLayout(new BorderLayout());

        //
        label = new JLabel();
        label.setIcon(new ImageIcon(Objects.requireNonNull(Utils.getImage("images/helpImage/symmetric.png"))));

        //
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout());
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton symmetricBtn = new RoundedButton();
        JButton asymmetricBtn = new RoundedButton();
        JButton hashBtn = new RoundedButton();
        JButton pbeBtn = new RoundedButton();

        symmetricBtn.setText("Symmetric helper");
        asymmetricBtn.setText("Asymmetric helper");
        hashBtn.setText("Hash helper");
        pbeBtn.setText("PBE helper");

        symmetricBtn.addActionListener(e -> open("symmetric"));
        asymmetricBtn.addActionListener(e -> open("asymmetric"));
        hashBtn.addActionListener(e -> open("hash"));
        pbeBtn.addActionListener(e -> open("pbe"));

        btnPanel.add(symmetricBtn);
        btnPanel.add(asymmetricBtn);
        btnPanel.add(hashBtn);
        btnPanel.add(pbeBtn);
        //
        this.add(label, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        // set up
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
    }

    // methods
    public static HelpFrame create() {
        return new HelpFrame();
    }

    public HelpFrame open(String name) {
        label.setIcon(new ImageIcon(Objects.requireNonNull(Utils.getImage("images/helpImage/" + name + ".png"))));
        return this;
    }

}
