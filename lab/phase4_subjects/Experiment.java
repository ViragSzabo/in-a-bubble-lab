package phase4_subjects;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;
import phase2_training.Cleaners.CleaningTeam;
import phase2_training.Cleaners.FoamMaster;
import phase2_training.Cleaners.SoapySquad;

import java.util.Collections;
import java.util.List;

public class Experiment {

    private final Convert converter = new Convert();

    /** Runs experiment for given data, structure, and algorithm. */
    public <T extends Comparable<T>> String runExperiment(List<T> data, String structure, String algorithm) {
        long startTime = System.nanoTime();
        String result = "";

        switch (structure) {
            case "Queue" -> result = runOnQueue(converter.toQueue(data), algorithm);
            case "Stack" -> result = runOnStack(converter.toStack(data), algorithm);
            case "Set" -> result = runOnSet(converter.toSet(data), algorithm);
            default -> throw new IllegalArgumentException("Unknown structure: " + structure);
        }

        double durationSeconds = (System.nanoTime() - startTime) / 1_000_000_000.0;
        return result + String.format("\n⏱ Execution time: %.3f seconds", durationSeconds);
    }

    // ===== Structure-specific helpers =====
    private <T extends Comparable<T>> String runOnQueue(FlowMaster<T> queue, String algorithm) {
        switch (algorithm) {
            case "Bubble", "Smart Bubble" -> Collections.sort(queue.toList());
            case "LabScanner", "LabSniper" -> queue.toList().contains((T) "test");
            case "SoapySquad" -> new SoapySquad().cleanQueue(queue);
            case "FoamMaster" -> new FoamMaster().cleanQueue(queue);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
        return algorithm + " applied to Queue";
    }

    private <T extends Comparable<T>> String runOnStack(PileDriver<T> stack, String algorithm) {
        switch (algorithm) {
            case "Bubble", "Smart Bubble" -> Collections.sort(stack.toList());
            case "LabScanner", "LabSniper" -> stack.toList().contains((T) "test");
            case "SoapySquad" -> new SoapySquad().cleanStack(stack);
            case "FoamMaster" -> new FoamMaster().cleanStack(stack);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
        return algorithm + " applied to Stack";
    }

    private <T extends Comparable<T>> String runOnSet(UniqueVault<T> set, String algorithm) {
        switch (algorithm) {
            case "Bubble", "Smart Bubble" -> Collections.sort(set.toList());
            case "LabScanner", "LabSniper" -> set.toList().contains((T) "test");
            case "SoapySquad" -> new SoapySquad().cleanSet(set);
            case "FoamMaster" -> new FoamMaster().cleanSet(set);
            default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
        return algorithm + " applied to Set";
    }
}