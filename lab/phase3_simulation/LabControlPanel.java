package phase3_simulation;

import phase4_subjects.DatasetManagement;
import phase4_subjects.Experiment;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class LabControlPanel  extends JFrame {
    private final JComboBox<String> structureCombo;
    private final JComboBox<String> algorithmCombo;
    private final JTextArea outputArea;
    private File importedFile;
    private java.util.List<String> importedData;

    public LabControlPanel() {
        super("\uD83E\uDEE7 In A Bubble Lab - Control Panel");

        setLayout(new BorderLayout());

        // Top panel: Dataset
        JPanel topPanel = new JPanel();
        JButton importButton = new JButton("Import Dataset");
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
        JButton runButton = new JButton("Run");
        JButton resetButton = new JButton("Reset");
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
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.setText("Imported: " + importedFile.getName() +
                    "\nTotal items: " + importedData.size());
        }
    }

    private void runExperiment() {
        if (importedData == null || importedData.isEmpty()) {
            outputArea.append("\nPlease import a dataset first!");
            return;
        }

        String structure = (String) structureCombo.getSelectedItem();
        String algorithm = (String) algorithmCombo.getSelectedItem();

        Experiment experiment = new Experiment();
        String result = experiment.runExperiment(importedData, Objects.requireNonNull(structure), algorithm);
        outputArea.append("\n" + result);
    }

    private void resetDataset() {
        if (importedFile != null) {
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.append("\nDataset reset! Total items: " + importedData.size());
        } else {
            outputArea.append("\nNo dataset to reset.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LabControlPanel::new);
    }
}