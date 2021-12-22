package TinyCryptor.view.helper;

import TinyCryptor.utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class InOutBoxPanel extends RoundedPanel {
    // fields
    private JTextArea textArea;
    private JFileChooser fileChooser;
    private File selectedFile;
    private JButton chooseFileDialogBtn;
    private JButton cancelFileBtn;
    private JButton copyBtn;
    private JButton pasteBtn;
    private JButton deleteBtn;

    // constructor
    public InOutBoxPanel(String title) {
        // set up
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), title));
        // create component
        JScrollPane scrollPane = createScrollPane();
        JPanel processPanel = createProcessPanel();
        // add component
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(processPanel, BorderLayout.SOUTH);
    }

    // methods
    // helper methods to create view
    private JScrollPane createScrollPane() {
        textArea = new JTextArea(5, 45);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    private JPanel createProcessPanel() {
        JPanel processPanel = new JPanel();
        processPanel.setBackground(Color.WHITE);
        processPanel.setLayout(new BorderLayout());
        processPanel.add(createFileProcessPanel(), BorderLayout.WEST);
        processPanel.add(createTextProcessPanel(), BorderLayout.EAST);
        return processPanel;
    }

    private JPanel createFileProcessPanel() {
        JPanel fileProcessPanel = new JPanel();
        fileProcessPanel.setBackground(Color.WHITE);
        fileProcessPanel.setLayout(new BoxLayout(fileProcessPanel, BoxLayout.X_AXIS));
        fileProcessPanel.setBorder(new EmptyBorder(3, 0, 3, 0));
        fileChooser = new JFileChooser();

        chooseFileDialogBtn = new RoundedButton();
        cancelFileBtn = new RoundedButton();

        chooseFileDialogBtn.setIcon(Utils.getImageIcon("images/icon/chooseFile.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        cancelFileBtn.setIcon(Utils.getImageIcon("images/icon/cancelFile.png", 16, 16, Image.SCALE_AREA_AVERAGING));

        chooseFileDialogBtn.setToolTipText("choose file");
        cancelFileBtn.setToolTipText("cancel file");

        cancelFileBtn.setEnabled(false);

        chooseFileDialogBtn.addActionListener(chooseFileDialog());
        cancelFileBtn.addActionListener(cancelFile());

        fileProcessPanel.add(chooseFileDialogBtn);
        fileProcessPanel.add(cancelFileBtn);
        return fileProcessPanel;
    }

    private JPanel createTextProcessPanel() {
        JPanel textProcessPanel = new JPanel();
        textProcessPanel.setBackground(Color.WHITE);
        textProcessPanel.setLayout(new BoxLayout(textProcessPanel, BoxLayout.X_AXIS));
        textProcessPanel.setBorder(new EmptyBorder(3, 0, 3, 0));

        copyBtn = new RoundedButton();
        pasteBtn = new RoundedButton();
        deleteBtn = new RoundedButton();

        copyBtn.setIcon(Utils.getImageIcon("images/icon/copy.png", 16, 16, Image.SCALE_REPLICATE));
        pasteBtn.setIcon(Utils.getImageIcon("images/icon/paste.png", 16, 16, Image.SCALE_REPLICATE));
        deleteBtn.setIcon(Utils.getImageIcon("images/icon/delete.png", 16, 16, Image.SCALE_REPLICATE));

        copyBtn.setToolTipText("copy text");
        pasteBtn.setToolTipText("paste text");
        deleteBtn.setToolTipText("delete text");

        copyBtn.addActionListener(copyText());
        pasteBtn.addActionListener(pasteText());
        deleteBtn.addActionListener(deleteText());

//        textProcessPanel.add(base64Btn);
        textProcessPanel.add(copyBtn);
        textProcessPanel.add(pasteBtn);
        textProcessPanel.add(deleteBtn);
        return textProcessPanel;
    }

    // action handler
    private ActionListener chooseFileDialog() {
        return e -> {
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName();
                if (fileName.length() > 25) {
                    fileName = fileName.substring(0, 25) + "...";
                }
                chooseFileDialogBtn.setText(fileName);
                cancelFileBtn.setEnabled(true);
            }
        };
    }

    private ActionListener openFolder() {
        return e -> {
            try {
                Desktop.getDesktop().open(selectedFile.getParentFile());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
    }

    private ActionListener cancelFolder() {
        return e -> {
            chooseFileDialogBtn.setText("");
            chooseFileDialogBtn.setEnabled(false);
            fileChooser.setSelectedFile(null);
            selectedFile = null;
            cancelFileBtn.setEnabled(false);
        };
    }

    private ActionListener cancelFile() {
        return e -> {
            chooseFileDialogBtn.setText("");
            fileChooser.setSelectedFile(null);
            selectedFile = null;
            cancelFileBtn.setEnabled(false);
        };
    }

    private ActionListener copyText() {
        return e -> Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(textArea.getText()), null);
    }

    private ActionListener pasteText() {
        return e -> {
            try {
                Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                DataFlavor dataFlavor = DataFlavor.stringFlavor;
                if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
                    Object text = systemClipboard.getData(dataFlavor);
                    textArea.setText((String) text);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        };
    }

    private ActionListener deleteText() {
        return e -> textArea.setText("");
    }

    // set up
    public InOutBoxPanel setRow(int row) {
        this.textArea.setRows(row);
        return this;
    }

    public InOutBoxPanel hideFileProcessPanel() {
        chooseFileDialogBtn.setVisible(false);
        cancelFileBtn.setVisible(false);
        return this;
    }

    public InOutBoxPanel blockChooseFileBtn() {
        this.chooseFileDialogBtn.setEnabled(false);
        return this;
    }

    public InOutBoxPanel setOpenFolderBtn() {
        chooseFileDialogBtn.setToolTipText("open folder");
        chooseFileDialogBtn.setIcon(Utils.getImageIcon("images/icon/openFolder.png", 16, 16, Image.SCALE_AREA_AVERAGING));
        chooseFileDialogBtn.removeActionListener(chooseFileDialogBtn.getActionListeners()[0]);
        chooseFileDialogBtn.addActionListener(openFolder());
        cancelFileBtn.removeActionListener(cancelFileBtn.getActionListeners()[0]);
        cancelFileBtn.addActionListener(cancelFolder());
        return this;
    }

    public InOutBoxPanel hideTextProcessPanel() {
        copyBtn.setVisible(false);
        pasteBtn.setVisible(false);
        deleteBtn.setVisible(false);
        return this;
    }

    public InOutBoxPanel blockTextArea() {
        textArea.setEditable(false);
        return this;
    }

    // get and set

    public String getText() {
        return this.textArea.getText();
    }

    public File getFile() {
        return this.selectedFile;
    }

    public void setText(String text) {
        this.textArea.setText(text);
    }

    public void setFile(File file) {
        chooseFileDialogBtn.setText(file.getName());
        chooseFileDialogBtn.setEnabled(true);
        fileChooser.setCurrentDirectory(file);
        fileChooser.setSelectedFile(file);
        selectedFile = file;
        cancelFileBtn.setEnabled(true);
    }
}