package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Comparator;

public interface ResearcherTeam
{
    <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target, Comparator<? super T> comparator);
    <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target, Comparator<? super T> comparator);
    <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target, Comparator<? super T> comparator);

    String getTeamName();
    String getMethodName();

    <T extends Comparable<T>> int searchQueue(FlowMaster<T> q, T target);
    <T extends Comparable<T>> int searchStack(PileDriver<T> s, T target);
    <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target);
}