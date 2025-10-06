package phase2_training;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.List;

public class SoapySquad implements CleaningTeam {

    @Override
    public <T extends Comparable<T>> void cleanQueue(FlowMaster<T> flowMaster) {
        int n = flowMaster.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] queue = (T[]) new Comparable[n];

        for (int i = 0; i < n; i++) {
            queue[i] = flowMaster.dequeue();
        }

        bubbleSort(queue);

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

        bubbleSort(stack);

        for (T item : stack) {
            pileDriver.push(item);
        }
    }

    @Override
    public <T extends Comparable<T>> void cleanSet(UniqueVault<T> uniqueVault) {
        List<T> list = uniqueVault.toList();
        if (list.size() <= 1) return;

        @SuppressWarnings("unchecked")
        T[] arr = (T[]) list.toArray(new Comparable[0]);

        bubbleSort(arr);

        uniqueVault.clear();
        for (T item : arr) {
            uniqueVault.add(item);
        }
    }

    private <T extends Comparable<T>> void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    @Override
    public String getTeamName() {
        return "Soapy Squad";
    }

    @Override
    public String getMethodName() {
        return "Classic Bubble Sort";
    }
}