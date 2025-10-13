package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LabScanner implements ResearcherTeam {

    // With comparator
    @Override
    public <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target, Comparator<? super T> comparator) {
        var list = flowMaster.toList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) return i;
        }
        return -1;
    }

    @Override
    public <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target, Comparator<? super T> comparator) {
        var list = pileDriver.toList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) return i;
        }
        return -1;
    }

    // Without comparator
    @Override
    public <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target) {
        var list = flowMaster.toList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) return i;
        }
        return -1;
    }

    @Override
    public <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target) {
        var list = pileDriver.toList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) return i;
        }
        return -1;
    }

    @Override
    public <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target) {
        return uniqueVault.contains(target);
    }

    @Override
    public <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target, Comparator<? super T> comparator) {
        List<T> list = uniqueVault.toList();
        for (T element : list) {
            if (comparator.compare(element, target) == 0) return true;
        }
        return false;
    }

    @Override
    public String getTeamName() {
        return "Scanner";
    }

    @Override
    public String getMethodName() {
        return "Sequential Search";
    }
}