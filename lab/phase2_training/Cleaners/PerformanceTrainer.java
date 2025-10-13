package phase2_training.Cleaners;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Comparator;

public class PerformanceTrainer {

    // Natural order
    public static void trainTeam(CleaningTeam team,
                                 FlowMaster<Integer> flowMaster,
                                 PileDriver<Integer> pileDriver,
                                 UniqueVault<Integer> uniqueVault) {
        System.out.println("\n--- " + team.getTeamName() + " Training (" + team.getMethodName() + ") ---");
        long startTime = System.currentTimeMillis();
        team.cleanQueue(flowMaster);
        team.cleanStack(pileDriver);
        team.cleanSet(uniqueVault);
        long endTime = System.currentTimeMillis();
        double durationMs = (endTime - startTime) / 1000.0;
        System.out.printf("%s completed training in %.3f ms 🫧%n", team.getTeamName(), durationMs);
    }

    // With custom comparator
    public static void trainTeam(CleaningTeam team,
                                 FlowMaster<Integer> flowMaster,
                                 PileDriver<Integer> pileDriver,
                                 UniqueVault<Integer> uniqueVault,
                                 Comparator<? super Integer> comparator) {
        System.out.println("\n--- " + team.getTeamName() + " Training (" + team.getMethodName() + " with Comparator) ---");
        long startTime = System.currentTimeMillis();
        team.cleanQueue(flowMaster, comparator);
        team.cleanStack(pileDriver, comparator);
        team.cleanSet(uniqueVault, comparator);
        long endTime = System.currentTimeMillis();
        double durationMs = (endTime - startTime) / 1000.0;
        System.out.printf("%s completed comparator-based training in %.3f ms 🫧%n", team.getTeamName(), durationMs);
    }
}