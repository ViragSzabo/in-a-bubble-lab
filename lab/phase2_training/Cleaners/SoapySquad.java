package phase2_training.Cleaners;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.Comparator;
import java.util.List;

public class SoapySquad implements CleaningTeam {

    @Override
    public <T extends Comparable<T>> void cleanQueue(FlowMaster<T> flowMaster) {
        int n = flowMaster.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] queue = (T[]) new Comparable[n];
        for (int i = 0; i < n; i++) queue[i] = flowMaster.dequeue();

        bubbleSort(queue, Comparator.naturalOrder());

        for (T item : queue) flowMaster.enqueue(item);
    }

    @Override
    public <T extends Comparable<T>> void cleanStack(PileDriver<T> pileDriver) {
        int n = pileDriver.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] stack = (T[]) new Comparable[n];
        for (int i = 0; i < n; i++) stack[i] = pileDriver.pop();

        bubbleSort(stack, Comparator.naturalOrder());

        for (T item : stack) pileDriver.push(item);
    }

    @Override
    public <T extends Comparable<T>> void cleanSet(UniqueVault<T> uniqueVault) {
        List<T> list = uniqueVault.toList();
        if (list.size() <= 1) return;

        @SuppressWarnings("unchecked")
        T[] arr = (T[]) list.toArray(new Comparable[0]);

        bubbleSort(arr, Comparator.naturalOrder());

        uniqueVault.clear();
        for (T item : arr) uniqueVault.add(item);
    }

    // --- With comparator ---
    @Override
    public <T> void cleanQueue(FlowMaster<T> flowMaster, Comparator<? super T> comparator) {
        int n = flowMaster.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] queue = (T[]) new Object[n];
        for (int i = 0; i < n; i++) queue[i] = flowMaster.dequeue();

        bubbleSort(queue, comparator);

        for (T item : queue) flowMaster.enqueue(item);
    }

    @Override
    public <T> void cleanStack(PileDriver<T> pileDriver, Comparator<? super T> comparator) {
        int n = pileDriver.size();
        if (n <= 1) return;

        @SuppressWarnings("unchecked")
        T[] stack = (T[]) new Object[n];
        for (int i = 0; i < n; i++) stack[i] = pileDriver.pop();

        bubbleSort(stack, comparator);

        for (T item : stack) pileDriver.push(item);
    }

    @Override
    public <T> void cleanSet(UniqueVault<T> uniqueVault, Comparator<? super T> comparator) {
        List<T> list = uniqueVault.toList();
        if (list.size() <= 1) return;

        @SuppressWarnings("unchecked")
        T[] arr = (T[]) list.toArray();

        bubbleSort(arr, comparator);

        uniqueVault.clear();
        for (T item : arr) uniqueVault.add(item);
    }

    private <T> void bubbleSort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
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