package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LabSniper implements ResearcherTeam {

    // With comparator
    @Override
    public <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target, Comparator<? super T> comparator) {
        List<T> list = flowMaster.toList();
        list.sort(comparator);
        int index = Collections.binarySearch(list, target, comparator);
        return index >= 0 ? index : -1;
    }

    @Override
    public <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target, Comparator<? super T> comparator) {
        List<T> list = pileDriver.toList();
        list.sort(comparator);
        int index = Collections.binarySearch(list, target, comparator);
        return index >= 0 ? index : -1;
    }

    // Without comparator (uses natural ordering)
    @Override
    public <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target) {
        List<T> list = flowMaster.toList();
        Collections.sort(list); // uses natural ordering
        int index = Collections.binarySearch(list, target);
        return index >= 0 ? index : -1;
    }

    @Override
    public <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target) {
        List<T> list = pileDriver.toList();
        Collections.sort(list); // uses natural ordering
        int index = Collections.binarySearch(list, target);
        return index >= 0 ? index : -1;
    }

    @Override
    public <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target) {
        // Convert to list
        List<T> list = uniqueVault.toList();

        if (list.isEmpty()) return false; // empty vault check

        // Sort list (natural ordering)
        Collections.sort(list);

        // Binary search
        int index = Collections.binarySearch(list, target);
        return index >= 0;
    }

    @Override
    public <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target, Comparator<? super T> comparator) {
        List<T> list = uniqueVault.toList();

        if (list.isEmpty()) return false;

        // Sort list using comparator
        list.sort(comparator);

        // Binary search with comparator
        int index = Collections.binarySearch(list, target, comparator);
        return index >= 0;
    }

    @Override
    public String getTeamName() {
        return "Sniper";
    }

    @Override
    public String getMethodName() {
        return "Binary Search";
    }
}