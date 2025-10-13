package phase2_training.Researchers;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.TeamBuilding;
import phase1_teams.UniqueVault;
import phase2_training.Cleaners.Training;

import java.util.Comparator;

public class Studying {
    public static void main(String[] args) {
        System.out.println("--- Team Building ---");
        TeamBuilding.main(null);

        System.out.println("\n--- Cleaning Crew Training ---");
        Training.main(null);

        System.out.println("\n--- Researchers Study Time ---");

        // Setup data structures
        FlowMaster<Integer> queue = new FlowMaster<>();
        PileDriver<Integer> stack = new PileDriver<>();
        UniqueVault<Integer> set = new UniqueVault<>();

        for(int i = 1; i <= 10; i++) {
            queue.enqueue(i);
            stack.push(i);
            set.add(i);
        }

        // Setup research teams
        LabScanner scanner = new LabScanner();
        LabSniper sniper = new LabSniper();

        ResearchLab labScanner = new ResearchLab(scanner);
        ResearchLab labSniper = new ResearchLab(sniper);

        // Custom comparator for demonstration (descending order)
        Comparator<Integer> descComparator = (a, b) -> b - a;

        // Researching: Custom comparator for demonstration (DESC)
        System.out.println("\n--- Scanners Search ---");
        labScanner.demonstrateSearchQueue(queue, 7);
        labScanner.demonstrateSearchStack(stack, 3);
        labScanner.demonstrateSearchQueue(queue, 7, descComparator);
        labScanner.demonstrateSearchStack(stack, 3, descComparator);
        labScanner.demonstrateSearchOnHashSet(set, 10);

        System.out.println("\n--- Snipers Search ---");
        labSniper.demonstrateSearchQueue(queue, 7);
        labSniper.demonstrateSearchStack(stack, 3);
        labSniper.demonstrateSearchQueue(queue, 7, descComparator);
        labSniper.demonstrateSearchStack(stack, 3, descComparator);
        labSniper.demonstrateSearchOnHashSet(set, 10);
    }
}