package phase2_training;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.List;

public class SoapySquad {
    public void cleanQueue(FlowMaster<Integer> flowMaster) {
        // Convert queue to a list
        int n = flowMaster.size();
        if (n <= 1) return;
        Integer[] queue = new Integer[n];
        for (int i = 0; i < n; i++) {
            queue[i] = flowMaster.dequeue();
        }

        // Bubble sort
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (queue[j] > queue[j+1]) {
                    int temp = queue[j];
                    queue[j] = queue[j+1];
                    queue[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        for (Integer i : queue) { flowMaster.enqueue(i); }
    }

    public void cleanStack(PileDriver<Integer> pileDriver) {
        // Pop all elements into an array
        int n = pileDriver.size();
        if (n <= 1) return;
        Integer[] stack = new Integer[n];
        for (int i = 0; i < n; i++) {
            stack[i] = pileDriver.pop();
        }

        // Bubble sort
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (stack[j] > stack[j+1]) {
                    int temp = stack[j];
                    stack[j] = stack[j+1];
                    stack[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        for(Integer i : stack) pileDriver.push(i);
    }

    public void cleanSet(UniqueVault<Integer> uniqueVault) {
        // Extract all elements into a list
        List<Integer> set = uniqueVault.toList();
        int n = uniqueVault.size();
        if (n <= 1) return;

        // Bubble sort
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (set.get(j) > set.get(j+1)) {
                    int temp = set.get(j);
                    set.set(j, set.get(j+1));
                    set.set(j+1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        set.clear();
        for(Integer i : set) {
            uniqueVault.add(i);
        }
    }
}