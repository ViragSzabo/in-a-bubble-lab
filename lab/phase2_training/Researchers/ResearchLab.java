package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Comparator;

public class ResearchLab {
    private final ResearcherTeam researchers;

    public ResearchLab(ResearcherTeam researchers) {
        this.researchers = researchers;
    }

    // Demonstrate search without comparator
    public <T extends Comparable<T>> void demonstrateSearchQueue(FlowMaster<T> q, T target) {
        int index = researchers.searchQueue(q, target);
        System.out.println(researchers.getTeamName() + researchers.getMethodName() + " using " + " found at index: " + index);
    }

    public <T extends Comparable<T>> void demonstrateSearchStack(PileDriver<T> s, T target) {
        int index = researchers.searchStack(s, target);
        System.out.println(researchers.getTeamName() + " using " + researchers.getMethodName() + " found at index: " + index);
    }

    public <T extends Comparable<T>> void demonstrateSearchOnHashSet(UniqueVault<T> set, T target) {
        boolean found = researchers.searchSet(set, target);
        System.out.println(researchers.getTeamName() + " using " + researchers.getMethodName() + " found? " + found);
    }

    // Demonstrate search using a comparator
    public <T extends Comparable<T>> void demonstrateSearchQueue(FlowMaster<T> q, T target, Comparator<? super T> comparator) {
        int index = researchers.searchQueue(q, target, comparator);
        System.out.println(researchers.getTeamName() + " using " + researchers.getMethodName() + " (comparator) found at index: " + index);
    }

    public <T extends Comparable<T>> void demonstrateSearchStack(PileDriver<T> s, T target, Comparator<? super T> comparator) {
        int index = researchers.searchStack(s, target, comparator);
        System.out.println(researchers.getTeamName() + " using " + researchers.getMethodName() + " (comparator) found at index: " + index);
    }
}