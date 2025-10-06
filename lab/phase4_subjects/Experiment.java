package phase4_subjects;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Collections;
import java.util.List;

public class Experiment {
    private final Convert converter = new Convert();

    public String runExperiment(List<String> data, String structure, String algorithm) {
        long start = System.nanoTime();

        switch (structure) {
            case "Queue" -> {
                FlowMaster<String> queue = converter.toQueue(data);
                applyAlgorithm(queue, algorithm);
            }
            case "Stack" -> {
                PileDriver<String> stack = converter.toStack(data);
                applyAlgorithm(stack, algorithm);
            }
            case "Set" -> {
                UniqueVault<String> set = converter.toSet(data);
                applyAlgorithm(set, algorithm);
            }
            default -> throw new IllegalArgumentException("Unknown structure: " + structure);
        }

        return String.format(algorithm, structure);
    }

    @SuppressWarnings({"unchecked"})
    private void applyAlgorithm(Object structure, String algorithm) {
        switch (algorithm) {
            case "Bubble", "Smart Bubble" -> {
                if (structure instanceof FlowMaster<?> q) {
                    List list = q.toList();
                    Collections.sort((List<String>) list);
                } else if (structure instanceof PileDriver<?> s) {
                    List list = s.toList();
                    Collections.sort((List<String>) list);
                } else if (structure instanceof UniqueVault<?> set) {
                    List list = set.toList();
                    Collections.sort((List<String>) list);
                }
            }
            case "LabScanner", "LabSniper" -> {
                String target = "test";
                if (structure instanceof FlowMaster<?> q) {
                    q.toList().contains(target);
                } else if (structure instanceof PileDriver<?> s) {
                    s.toList().contains(target);
                } else if (structure instanceof UniqueVault<?> set) {
                    set.toList().contains(target);
                }
            }
            case "SoapySquad", "FoamMaster" -> {
                if (structure instanceof FlowMaster<?> q) {
                    Collections.shuffle((List<?>) q.toList());
                } else if (structure instanceof PileDriver<?> s) {
                    Collections.shuffle((List<?>) s.toList());
                } else if (structure instanceof UniqueVault<?> set) {
                    Collections.shuffle((List<?>) set.toList());
                }
            }
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
    }
}