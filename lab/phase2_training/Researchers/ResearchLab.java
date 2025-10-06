package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

public class ResearchLab {
    private ResearcherTeam researchers;

    public ResearchLab(ResearcherTeam researchers) {
        this.researchers = researchers;
    }

    public <T extends Comparable<T>> void demonstrateSearchQueue(FlowMaster<T> q, T target) {
        System.out.println(researchers.getTeamName() +
                " using " + researchers.getMethodName() +
                " found at index: " + researchers.searchQueue(q, target));
    }

    public <T extends Comparable<T>> void demonstrateSearchStack(PileDriver<T> s, T target) {
        System.out.println(researchers.getTeamName() +
                " using " + researchers.getMethodName() +
                " found at index: " + researchers.searchStack(s, target));
    }

    public <T extends Comparable<T>> void demonstrateSearchOnHashSet(UniqueVault<T> set, T target) {
        System.out.println(researchers.getTeamName() +
                " using " + researchers.getMethodName() +
                " found? "  + researchers.searchSet(set, target));
    }
}