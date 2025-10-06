package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

public class LabScanner implements ResearcherTeam {

    @Override
    public <T extends Comparable<T>> int searchQueue(FlowMaster<T> flowMaster, T target) {
        int size = flowMaster.size();
        for (int i = 0; i < size; i++) {
            T item = flowMaster.dequeue();
            flowMaster.enqueue(item);
            if(item.equals(target)) return i;
        }
        return -1;
    }

    @Override
    public <T extends Comparable<T>> int searchStack(PileDriver<T> pileDriver, T target) {
        int size = pileDriver.size();
        PileDriver<T> tempStack = new PileDriver<>();
        int index = -1;

        // Move all items to tempStack (reverse stack)
        for (int i = 0; i < size; i++) {
            tempStack.push(pileDriver.pop());
        }

        // Restore original stack and compute index from top
        for (int i = 0; i < size; i++) {
            T item = tempStack.pop();
            pileDriver.push(item);
            if(item.equals(target) && index == -1) {
                index = size - i - 1; // ✅ top = 0, bottom = size - 1
            }
        }

        return index;
    }

    @Override
    public <T extends Comparable<T>> boolean searchSet(UniqueVault<T> uniqueVault, T target) {
        return uniqueVault.contains(target);
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