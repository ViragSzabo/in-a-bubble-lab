package phase3_simulation;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;
import phase2_training.Cleaners.*;
import phase2_training.Researchers.LabScanner;
import phase2_training.Researchers.LabSniper;
import phase2_training.Researchers.ResearcherTeam;
import phase4_subjects.DatasetManagement;
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
                long startTime = System.nanoTime();
                outputArea.append("\n--------------------------------------------------\n");
                outputArea.append(String.format("🧪 Running experiment using %s + %s...\n\n", structure, researching));
                outputArea.append("📊 Data Summary\n---------------------------\n");
                outputArea.append(String.format("Items processed: %d\n", importedData.size()));

                List<String> dataCopy = List.copyOf(importedData);
                String target = dataCopy.get(dataCopy.size() / 2);
                outputArea.append(String.format("Target element: \"%s\"\n", target));
                outputArea.append("\n🧼 Cleaning Phase\n---------------------------\n");

                List<Integer> chartData = dataCopy.stream().map(String::length).toList();
                chart.setData(chartData, dataCopy);

                // Simulate progress
                for (int i = 0; i <= 100; i += 5) {
                    Thread.sleep(30);
                    publish(i);
                }

                // 🧼 Cleaning phase
                CleaningTeam cleaningTeam = switch (cleaning) {
                    case "SoapySquad" -> new SoapySquad();
                    case "FoamMaster" -> new FoamMaster();
                    default -> new SoapySquad();
                };

                outputArea.append("Team: " + cleaningTeam.getClass().getSimpleName() + "\n");
                outputArea.append("Result: Completed successfully\n");

                // 🧠 Research phase
                outputArea.append("\n🔍 Research Phase\n---------------------------\n");
                ResearcherTeam researcherTeam = switch (researching) {
                    case "LabSniper" -> new LabSniper();
                    case "LabScanner" -> new LabScanner();
                    default -> new LabSniper();
                };

                // 🧩 Structure loading, cleaning + searching
                switch (structure) {
                    case "Queue" -> {
                        FlowMaster<String> queue = new FlowMaster<>(String::compareTo);
                        for (String s : dataCopy) queue.enqueue(s);

                        cleaningTeam.cleanQueue(queue);
                        researcherTeam.searchQueue(queue, target);
                    }

                    case "Stack" -> {
                        PileDriver<String> stack = new PileDriver<>(String::compareTo);
                        for (String s : dataCopy) stack.push(s);

                        cleaningTeam.cleanStack(stack);
                        researcherTeam.searchStack(stack, target);
                    }

                    case "Set" -> {
                        UniqueVault<String> set = new UniqueVault<>(String::compareTo);
                        for (String s : dataCopy) set.add(s);

                        cleaningTeam.cleanSet(set);
                        researcherTeam.searchSet(set, target);
                    }
                }

                // 🧾 Research results
                long endTime = System.nanoTime();
                double duration = (endTime - startTime) / 1_000_000.0;
                outputArea.append(String.format("\n⏱ Total Duration: %.2f ms\n", duration));
                outputArea.append("✅ Experiment complete!\n");
                outputArea.append("--------------------------------------------------\n\n");

                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                progressBar.setValue(chunks.getLast());
            }

            @Override
            protected void done() {
                outputArea.append("✅ Experiment complete!\n\n");
            }
        };

        worker.execute();
    }

    private void resetDataset(JButton runButton) {
        if (importedFile != null) {
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.append("\n🔄 Dataset reset! Total items: " + importedData.size());
            runButton.setEnabled(true);
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(LabControlPanel::new);
    }
}