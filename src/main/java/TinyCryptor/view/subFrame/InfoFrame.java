package TinyCryptor.view.subFrame;

import TinyCryptor.utils.Utils;
import TinyCryptor.view.helper.HyperLinkLabel;

import javax.swing.*;
import java.awt.*;

public class InfoFrame extends JDialog {

    // fields
    private JLabel info;
    private HyperLinkLabel facebookHyperLink;
    private HyperLinkLabel githubHyperLink;

    private final String information =
            "<html>" +
                    "<p style=\"padding-bottom:3; font-size:15\">TinyCryptor is a small Java cryptography project. </p>" +
                    "<p style=\"padding-bottom:3; font-size:15\">Subject: Cryptography </p>" +
                    "<p style=\"padding-bottom:3; font-size:15\">Instructor: M.S Phan Dinh Long </p>" +
                    "<p style=\"padding-bottom:3; font-size:15\">Owner: Le Tan Phat </p>" +
                    "<html>";
    private final String githubLink = "https://github.com/LeTanPhat857/TinyCryptor";
    private final String facebookLink = "https://www.facebook.com/tanphat.le.9085790";

    // constructor
    private InfoFrame() {
        this.setIconImage(Utils.getImage("images/icon/cryptography.png", 16, 16, Image.SCALE_SMOOTH));
        this.setTitle("About TinyCryptor");
        this.getContentPane().setLayout(new BorderLayout());
        this.setModal(true);
        this.setResizable(false);

        this.add(createInfoPanel(), BorderLayout.CENTER);
        this.add(createContactPanel(), BorderLayout.SOUTH);
        // set up
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    // methods
    public static InfoFrame create() {
        return new InfoFrame();
    }

    public void setVisible(boolean bool) {
        this.setVisible(bool);
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        info = new JLabel(information);
        infoPanel.add(info);
        return infoPanel;
    }

    private JPanel createContactPanel() {
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BorderLayout());
        contactPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Contact me"), BorderFactory.createEmptyBorder(5, 20, 5, 20)));
        facebookHyperLink = new HyperLinkLabel("Facebook | LeTanPhat", facebookLink, "visit " + facebookLink);
        githubHyperLink = new HyperLinkLabel("GitHub | TinyCryptor", githubLink, "visit " + githubLink);
        facebookHyperLink.setFont(new Font("Arial", Font.ITALIC, 15));
        githubHyperLink.setFont(new Font("Arial", Font.ITALIC, 15));
        contactPanel.add(facebookHyperLink, BorderLayout.WEST);
        contactPanel.add(githubHyperLink, BorderLayout.EAST);
        return contactPanel;
    }
}
