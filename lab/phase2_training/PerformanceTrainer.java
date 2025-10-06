package phase2_training;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

public class PerformanceTrainer {
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
}