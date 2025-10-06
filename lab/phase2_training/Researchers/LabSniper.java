package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Collections;
import java.util.List;

public class LabSniper implements ResearcherTeam {

    @Override
    public <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target) {
        List<T> list = flowMasterToList(flowMaster);
        Collections.sort(list);
        int index = Collections.binarySearch(list, target);
        restoreQueue(flowMaster, list);
        return index >= 0 ? index : -1;
    }

    @Override
    public <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target) {
        int n = pileDriver.size();
        for (int i = n - 1; i >= 0; i--) { // bottom = 0
            if (pileDriver.peekAt(i).compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target) {
        return uniqueVault.contains(target);
    }

    @Override
    public String getTeamName() {
        return "Sniper";
    }

    @Override
    public String getMethodName() {
        return "Binary Search";
    }

    // Helper methods
    private <T> List<T> flowMasterToList(FlowMaster<T> q) {
        int n = q.size();
        List<T> list = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) list.add(q.dequeue());
        return list;
    }

    private <T> void restoreQueue(FlowMaster<T> q, List<T> list) {
        for (T item : list) q.enqueue(item);
    }

    private <T> List<T> stackToList(PileDriver<T> s) {
        int n = s.size();
        List<T> list = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) list.add(s.peekAt(i));
        return list;
    }

    private <T> void restoreStack(PileDriver<T> s, List<T> list) {
        for (int i = list.size() - 1; i >= 0; i--) s.push(list.get(i));
    }
}