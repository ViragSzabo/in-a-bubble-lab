package phase3_simulation;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LabControlPanel  extends JFrame {
    private final JButton importButton;
    private final JButton runButton;
    private final JButton resetButton;
    private final JComboBox<String> structureCombo;
    private final JComboBox<String> algorithmCombo;
    private final JTextArea outputArea;
    private File importedFile;

    public LabControlPanel() {
        super("In A Bubble Lab - Control Panel");

        setLayout(new BorderLayout());

        // Top panel: Dataset
        JPanel topPanel = new JPanel();
        importButton = new JButton("Import Dataset");
        topPanel.add(importButton);
        add(topPanel, BorderLayout.NORTH);

        // Middle panel: selection
        JPanel middlePanel = new JPanel(new GridLayout(2,2,10,10));
        middlePanel.add(new JLabel("Choose Data Structure:"));
        structureCombo = new JComboBox<>(new String[]{"Queue", "Stack", "Set"});
        middlePanel.add(structureCombo);

        middlePanel.add(new JLabel("Choose Algorithm:"));
        algorithmCombo = new JComboBox<>(new String[]{
                "Bubble", "Smart Bubble", "LabScanner", "LabSniper", "SoapySquad", "FoamMaster"
        });
        middlePanel.add(algorithmCombo);

        add(middlePanel, BorderLayout.CENTER);

        // Bottom panel: run & output
        JPanel bottomPanel = new JPanel(new BorderLayout());
        runButton = new JButton("Run");
        resetButton = new JButton("Reset");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(runButton);
        buttonsPanel.add(resetButton);
        bottomPanel.add(buttonsPanel, BorderLayout.NORTH);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listeners
        importButton.addActionListener(e -> importDataset());
        runButton.addActionListener(e -> runExperiment());
        resetButton.addActionListener(e -> resetDataset());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void importDataset() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION) {
            importedFile = fileChooser.getSelectedFile();
            outputArea.setText("Imported: " + importedFile.getName());
            // TODO: read file into a list
        }
    }

    private void runExperiment() {
        String structure = (String) structureCombo.getSelectedItem();
        String algorithm = (String) algorithmCombo.getSelectedItem();
        outputArea.append("\nRunning " + algorithm + " on " + structure);
        // TODO: Convert dataset to chosen structure
        // TODO: Run chosen algorithm
        // TODO: Display execution time and partial results
    }

    private void resetDataset() {
        outputArea.append("\nDataset reset!");
        // TODO: rebuild original dataset
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LabControlPanel::new);
    }
}