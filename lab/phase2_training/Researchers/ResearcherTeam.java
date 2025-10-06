package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

public interface ResearcherTeam
{
    <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target);
    <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target);
    <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target);

    String getTeamName();
    String getMethodName();
}