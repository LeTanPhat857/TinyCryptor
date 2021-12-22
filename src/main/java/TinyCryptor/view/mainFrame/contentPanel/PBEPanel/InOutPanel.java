package TinyCryptor.view.mainFrame.contentPanel.PBEPanel;

import TinyCryptor.view.helper.InOutBoxPanel;

import javax.swing.*;

public class InOutPanel extends JPanel {
    // fields
    private InOutBoxPanel passwordInOutBoxPanel;
    private InOutBoxPanel saltInOutBoxPanel;
    private InOutBoxPanel inputInOutBoxPanel;
    private InOutBoxPanel outputInOutBoxPanel;

    // constructor
    public InOutPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInOutBoxPanel = new InOutBoxPanel("Password").setRow(5).hideFileProcessPanel();
        saltInOutBoxPanel = new InOutBoxPanel("Salt (Base64)").setRow(1).hideFileProcessPanel().blockTextArea();
        inputInOutBoxPanel = new InOutBoxPanel("Input").setRow(5).hideFileProcessPanel();
        outputInOutBoxPanel = new InOutBoxPanel("Output (Base64)").setRow(5).blockChooseFileBtn().blockTextArea().setOpenFolderBtn().hideFileProcessPanel();

        this.add(passwordInOutBoxPanel);
        this.add(saltInOutBoxPanel);
        this.add(inputInOutBoxPanel);
        this.add(outputInOutBoxPanel);
    }

    // methods
    public InOutBoxPanel getPasswordBox() {
        return passwordInOutBoxPanel;
    }

    public InOutBoxPanel getInputBox() {
        return inputInOutBoxPanel;
    }

    public InOutBoxPanel getOutputBox() {
        return outputInOutBoxPanel;
    }

    public InOutBoxPanel getSaltBox() {
        return saltInOutBoxPanel;
    }
}


