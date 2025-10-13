package phase2_training.Cleaners;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Comparator;

public interface CleaningTeam {

    // Natural ordering
    <T extends Comparable<T>> void cleanQueue(FlowMaster<T> flowMaster);
    <T extends Comparable<T>> void cleanStack(PileDriver<T> pileDriver);
    <T extends Comparable<T>> void cleanSet(UniqueVault<T> uniqueVault);

    // With custom comparator
    <T> void cleanQueue(FlowMaster<T> flowMaster, Comparator<? super T> comparator);
    <T> void cleanStack(PileDriver<T> pileDriver, Comparator<? super T> comparator);
    <T> void cleanSet(UniqueVault<T> uniqueVault, Comparator<? super T> comparator);

    String getTeamName();
    String getMethodName();
}