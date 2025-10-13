package phase3_simulation;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;
import phase2_training.Cleaners.*;
import phase2_training.Researchers.LabScanner;
import phase2_training.Researchers.LabSniper;
import phase2_training.Researchers.ResearcherTeam;
import phase4_subjects.DatasetManagement;
import phase4_subjects.SampleData;
import phase5_present.LabChart;
import phase5_present.LabUIStyle;
import phase5_present.Tabbed;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class LabControlPanel extends Tabbed {

    private JComboBox<String> structureCombo;
    private JComboBox<String> cleaningCombo;
    private JComboBox<String> researchingCombo;
    private JTextArea outputArea;
    private JProgressBar progressBar;
    private File importedFile;
    private List<String> importedData;
    private final LabChart chart;

    public LabControlPanel() {
        super();
        setTitle("🧫 In A Bubble Lab - Control Panel");
        LabUIStyle.applyGlobalStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 500));

        // Panels
        JPanel controlPanel = createControlPanel();
        JPanel visualizationPanel = new JPanel(new BorderLayout());

        // Chart
        chart = new LabChart();
        visualizationPanel.add(chart, BorderLayout.CENTER);

        // Tabs
        addLabTabs(controlPanel, visualizationPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Animated title
        new Timer(150, _ -> {
            String[] msgs = {"✨ Initializing...", "🔬 Loading equipment...", "🧪 Ready!"};
            int idx = (int) ((System.currentTimeMillis() / 1500) % msgs.length);
            setTitle("In A Bubble Lab – " + msgs[idx]);
        }).start();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dataset import
        JPanel topPanel = new JPanel();
        JButton importButton = new JButton("Import Dataset");
        importButton.setToolTipText("Select a CSV or TXT file to load your dataset");
        topPanel.add(importButton);
        panel.add(topPanel, BorderLayout.NORTH);

        // Structure & algorithm
        JPanel middlePanel = new JPanel(new GridLayout(3, 3, 10, 10));
        middlePanel.add(new JLabel("Choose Data Structure:"));

        structureCombo = new JComboBox<>(new String[]{"Queue", "Stack", "Set"});
        middlePanel.add(structureCombo);

        middlePanel.add(new JLabel("Choose Cleaning Algorithm:"));
        cleaningCombo = new JComboBox<>(new String[]{
                "SoapySquad", "FoamMaster",
        });
        middlePanel.add(cleaningCombo);

        middlePanel.add(new JLabel("Choose Research Algorithm:"));
        researchingCombo = new JComboBox<>(new String[]{
                "LabSniper", "LabScanner"
        });
        middlePanel.add(researchingCombo);

        panel.add(middlePanel, BorderLayout.CENTER);

        // Output & buttons
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setPreferredSize(new Dimension(500, 200));
        JPanel buttonsPanel = new JPanel();
        JButton runButton = new JButton("Run");
        JButton resetButton = new JButton("Reset");
        buttonsPanel.add(runButton);
        buttonsPanel.add(resetButton);
        bottomPanel.add(buttonsPanel, BorderLayout.NORTH);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        bottomPanel.add(progressBar, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Hover effects
        applyHoverEffect(runButton);
        applyHoverEffect(resetButton);

        // Actions
        importButton.addActionListener(_ -> importDataset(runButton));
        runButton.addActionListener(_ -> runExperimentInBackground());
        resetButton.addActionListener(_ -> resetDataset(runButton));

        runButton.setEnabled(false);

        return panel;
    }

    private void applyHoverEffect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(120, 150, 255));
                button.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIManager.getColor("control"));
                button.setForeground(Color.BLACK);
            }
        });
    }

    private void importDataset(JButton runButton) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            importedFile = fileChooser.getSelectedFile();
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.setText("📂 Imported: " + importedFile.getName() +
                    "\n📊 Total items: " + importedData.size());
            runButton.setEnabled(true);
        }
    }

    private void runExperimentInBackground() {
        if (importedData == null || importedData.isEmpty()) {
            outputArea.append("\n⚠️ Please import a dataset first!\n");
            return;
        }

        String structure = (String) structureCombo.getSelectedItem();
        String cleaning = (String) cleaningCombo.getSelectedItem();
        String researching = (String) researchingCombo.getSelectedItem();

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                outputArea.append("\n--------------------------------------------------\n");
                outputArea.append(String.format("🧪 Running experiment using %s + %s...\n\n", structure, researching));
                outputArea.append("📊 Data Summary\n---------------------------\n");
                outputArea.append(String.format("Items processed: %d\n", importedData.size()));

                List<SampleData> objectData = importedData.stream().map(SampleData::new).toList();
                int totalItems = objectData.size();
                SampleData targetObject = objectData.get(totalItems / 2);
                outputArea.append(String.format("Target element: \"%s\"\n", targetObject.getValue()));

                // Initialize chart
                List<Integer> chartData = objectData.stream().map(d -> d.getValue().length()).toList();
                chart.setData(chartData, importedData);

                // Cleaning phase
                outputArea.append("\n🧼 Cleaning Phase\n---------------------------\n");
                CleaningTeam cleaningTeam = switch (cleaning) {
                    case "FoamMaster" -> new FoamMaster();
                    default -> new SoapySquad();
                };
                long cleaningStart = System.nanoTime();
                processStructure(structure, objectData, 0, 50, cleaningTeam, null, null);
                long cleaningEnd = System.nanoTime();
                double cleaningDuration = (cleaningEnd - cleaningStart) / 1_000_000.0;

                // Research phase
                outputArea.append("\n🔍 Research Phase\n---------------------------\n");
                ResearcherTeam researcher = switch (researching) {
                    case "LabScanner" -> new LabScanner();
                    default -> new LabSniper();
                };
                long researchStart = System.nanoTime();
                processStructure(structure, objectData, 50, 100, null, researcher, targetObject);
                long researchEnd = System.nanoTime();
                double researchDuration = (researchEnd - researchStart) / 1_000_000.0;

                // Results
                outputArea.append(String.format("🧼 Cleaning Algorithm: %s - Duration: %.2f ms\n", cleaning, cleaningDuration));
                outputArea.append(String.format("🔍 Research Algorithm: %s - Duration: %.2f ms\n", researching, researchDuration));
                outputArea.append(String.format("\n⏱ Total Duration: %.2f ms\n", cleaningDuration + researchDuration));
                outputArea.append("--------------------------------------------------\n\n");

                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                outputArea.append("✅ Experiment complete!\n\n");
            }
        };
        worker.execute();
    }

    // Generalized processing for both cleaning and research
    private void processStructure(String structure, List<SampleData> data, int progressStart, int progressEnd,
                                  CleaningTeam cleaningTeam, ResearcherTeam researcher, SampleData targetObject) {
        int totalItems = data.size();
        int updateStep = Math.max(1, totalItems / 50);

        switch (structure) {
            case "Queue" -> {
                FlowMaster<SampleData> queue = new FlowMaster<>(SampleData::compareTo);
                for (int i = 0; i < totalItems; i++) {
                    queue.enqueue(data.get(i));
                    if (i % updateStep == 0 || i == totalItems - 1) {
                        publish(progressStart + (int) Math.round((i + 1) * (progressEnd - progressStart) / (double) totalItems));
                    }
                }
                if (cleaningTeam != null) cleaningTeam.cleanQueue(queue);
                if (researcher != null) researcher.searchQueue(queue, targetObject);
            }
            case "Stack" -> {
                PileDriver<SampleData> stack = new PileDriver<>(SampleData::compareTo);
                for (int i = 0; i < totalItems; i++) {
                    stack.push(data.get(i));
                    if (i % updateStep == 0 || i == totalItems - 1) {
                        publish(progressStart + (int) Math.round((i + 1) * (progressEnd - progressStart) / (double) totalItems));
                    }
                }
                if (cleaningTeam != null) cleaningTeam.cleanStack(stack);
                if (researcher != null) researcher.searchStack(stack, targetObject);
            }
            case "Set" -> {
                UniqueVault<SampleData> set = new UniqueVault<>(SampleData::compareTo);
                for (int i = 0; i < totalItems; i++) {
                    set.add(data.get(i));
                    if (i % updateStep == 0 || i == totalItems - 1) {
                        publish(progressStart + (int) Math.round((i + 1) * (progressEnd - progressStart) / (double) totalItems));
                    }
                }
                if (cleaningTeam != null) cleaningTeam.cleanSet(set);
                if (researcher != null) researcher.searchSet(set, targetObject);
            }
        }
    }

    private void publish(int i) {
        SwingUtilities.invokeLater(() -> progressBar.setValue(i));
    }

    private void resetDataset(JButton runButton) {
        if (importedFile != null) {
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.append("\n🔄 Dataset reset! Total items: " + importedData.size());

            // Refresh chart
            List<Integer> chartData = importedData.stream().map(String::length).toList();
            chart.setData(chartData, importedData);

            runButton.setEnabled(true);
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(LabControlPanel::new);
    }
}