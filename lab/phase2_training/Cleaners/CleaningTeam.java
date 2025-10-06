package phase2_training.Cleaners;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

public interface CleaningTeam {
    <T extends Comparable<T>> void cleanQueue(FlowMaster<T> flowMaster);
    <T extends Comparable<T>> void cleanStack(PileDriver<T> pileDriver);
    <T extends Comparable<T>> void cleanSet(UniqueVault<T> uniqueVault);
    String getTeamName();
    String getMethodName();
}