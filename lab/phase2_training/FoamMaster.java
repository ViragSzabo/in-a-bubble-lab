package phase2_training;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.List;

public class FoamMaster {
    public void smartCleanQueue(FlowMaster<Integer> flowMaster) {
        int n = flowMaster.size();
        if (n <= 1) return;

        Integer[] queue = new Integer[n];
        for (int i = 0; i < n; i++) {
            queue[i] = flowMaster.dequeue();
        }

        boolean swapped = true;
        int start = 0;
        int end = n - 1;

        while (swapped) {
            swapped = false;

            // Forward pass
            for (int i = start; i < end; i++) {
                if (queue[i] > queue[i + 1]) {
                    int temp = queue[i];
                    queue[i] = queue[i + 1];
                    queue[i + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;

            swapped = false;
            end--;

            // Backward pass
            for (int i = end - 1; i >= start; i--) {
                if (queue[i] > queue[i + 1]) {
                    int temp = queue[i];
                    queue[i] = queue[i + 1];
                    queue[i + 1] = temp;
                    swapped = true;
                }
            }

            start++;
        }

        for (Integer i : queue) flowMaster.enqueue(i);
    }

    public void smartCleanStack(PileDriver<Integer> pileDriver) {
        int n = pileDriver.size();
        if (n <= 1) return;

        Integer[] stack = new Integer[n];
        for (int i = 0; i < n; i++) {
            stack[i] = pileDriver.pop();
        }

        boolean swapped = true;
        int start = 0;
        int end = n - 1;

        while (swapped) {
            swapped = false;

            // Forward pass
            for (int i = start; i < end; i++) {
                if (stack[i] > stack[i + 1]) {
                    int temp = stack[i];
                    stack[i] = stack[i + 1];
                    stack[i + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;

            swapped = false;
            end--;

            // Backward pass
            for (int i = end - 1; i >= start; i--) {
                if (stack[i] > stack[i + 1]) {
                    int temp = stack[i];
                    stack[i] = stack[i + 1];
                    stack[i + 1] = temp;
                    swapped = true;
                }
            }

            start++;
        }

        for (Integer i : stack) pileDriver.push(i);
    }

    public void smartCleanSet(UniqueVault<Integer> uniqueVault) {
        List<Integer> list = uniqueVault.toList();
        int n = list.size();
        if (n <= 1) return;

        boolean swapped = true;
        int start = 0;
        int end = n - 1;

        while (swapped) {
            swapped = false;

            // Forward pass
            for (int i = start; i < end; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;

            swapped = false;
            end--;

            // Backward pass
            for (int i = end - 1; i >= start; i--) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }

            start++;
        }

        uniqueVault.clear();
        for (Integer num : list) uniqueVault.add(num);
    }
}