package phase2_training.Cleaners;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.List;

public class FoamMaster implements CleaningTeam {

    @Override
    public <T extends Comparable<T>> void cleanQueue(FlowMaster<T> flowMaster) {
        int n = flowMaster.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] queue = (T[]) new Comparable[n];

        for (int i = 0; i < n; i++) {
            queue[i] = flowMaster.dequeue();
        }

        smartBubbleSort(queue);

        for (T item : queue) {
            flowMaster.enqueue(item);
        }
    }

    @Override
    public <T extends Comparable<T>> void cleanStack(PileDriver<T> pileDriver) {
        int n = pileDriver.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] stack = (T[]) new Comparable[n];

        for (int i = 0; i < n; i++) {
            stack[i] = pileDriver.pop();
        }

        smartBubbleSort(stack);

        for (int i = n - 1; i >= 0; i--) {
            pileDriver.push(stack[i]);
        }
    }

    @Override
    public <T extends Comparable<T>> void cleanSet(UniqueVault<T> uniqueVault) {
        List<T> list = uniqueVault.toList();
        if (list.size() <= 1) return;

        @SuppressWarnings("unchecked")
        T[] arr = (T[]) list.toArray(new Comparable[0]);

        smartBubbleSort(arr);

        uniqueVault.clear();
        for (T item : arr) {
            uniqueVault.add(item);
        }
    }

    private <T extends Comparable<T>> void smartBubbleSort(T[] array) {
        boolean swapped = true;
        int start = 0;
        int end = array.length - 1;

        while (swapped) {
            swapped = false;

            for (int i = start; i < end; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
            swapped = false;
            end--;

            for (int i = end - 1; i >= start; i--) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            start++;
        }
    }

    @Override
    public String getTeamName() {
        return "Foam Master";
    }

    @Override
    public String getMethodName() {
        return "Smart Bubble Sort";
    }
}