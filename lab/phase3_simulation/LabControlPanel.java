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

                // Initialize chart
                List<Integer> chartData = dataCopy.stream().map(String::length).toList();
                chart.setData(chartData, dataCopy);


                // 🧼 Cleaning phase
                outputArea.append("\n🧼 Cleaning Phase\n---------------------------\n");
                long cleaningStart = System.nanoTime();

                CleaningTeam cleaningTeam = switch (cleaning) {
                    case "SoapySquad" -> new SoapySquad();
                    case "FoamMaster" -> new FoamMaster();
                    default -> new SoapySquad();
                };

                // Structure setup
                boolean[] results = new boolean[2]; // for sniper, scanner

                switch (structure) {
                    case "Queue" -> {
                        FlowMaster<String> queue = new FlowMaster<>(String::compareTo);
                        dataCopy.forEach(queue::enqueue);
                        cleaningTeam.cleanQueue(queue);
                    }
                    case "Stack" -> {
                        PileDriver<String> stack = new PileDriver<>(String::compareTo);
                        dataCopy.forEach(stack::push);
                        cleaningTeam.cleanStack(stack);
                    }
                    case "Set" -> {
                        UniqueVault<String> set = new UniqueVault<>(String::compareTo);
                        dataCopy.forEach(set::add);
                        cleaningTeam.cleanSet(set);
                    }
                }

                // 🧠 Research phase
                outputArea.append("\n🔍 Research Phase\n---------------------------\n");
                ResearcherTeam researcherTeam = switch (researching) {
                    case "LabSniper" -> new LabSniper();
                    case "LabScanner" -> new LabScanner();
                    default -> new LabSniper();
                };

                long cleaningEnd = System.nanoTime();
                double cleaningDuration = (cleaningEnd - cleaningStart) / 1_000_000.0;
                outputArea.append(String.format("✅ Cleaning completed in %.2f ms\n", cleaningDuration));

                // Phase 2: Research
                outputArea.append("\n🔍 Research Phase\n---------------------------\n");
                long researchStart = System.nanoTime();

                ResearcherTeam researcher = switch (researching) {
                    case "LabSniper" -> new LabSniper();
                    case "LabScanner" -> new LabScanner();
                    default -> new LabSniper();
                };

                switch (structure) {
                    case "Queue" -> {
                        FlowMaster<String> queue = new FlowMaster<>(String::compareTo);
                        dataCopy.forEach(queue::enqueue);
                        researcher.searchQueue(queue, target);
                    }
                    case "Stack" -> {
                        PileDriver<String> stack = new PileDriver<>(String::compareTo);
                        dataCopy.forEach(stack::push);
                        researcher.searchStack(stack, target);
                    }
                    case "Set" -> {
                        UniqueVault<String> set = new UniqueVault<>(String::compareTo);
                        dataCopy.forEach(set::add);
                        researcher.searchSet(set, target);
                    }
                }

                long researchEnd = System.nanoTime();
                double researchDuration = (researchEnd - researchStart) / 1_000_000.0;
                outputArea.append(String.format("✅ Research completed in %.2f ms\n", researchDuration));

                // 🧾 Research results
                outputArea.append(String.format("\n⏱ Total Duration: %.2f ms\n", cleaningDuration + researchDuration));
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