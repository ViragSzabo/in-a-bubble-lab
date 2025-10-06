package phase2_training.Researchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import static org.junit.jupiter.api.Assertions.*;

class ResearcherTeamTest {

    private FlowMaster<Integer> queue;
    private PileDriver<Integer> stack;
    private UniqueVault<Integer> set;
    private LabScanner scanner;
    private LabSniper sniper;

    @BeforeEach
    void setUp() {
        queue = new FlowMaster<>();
        stack = new PileDriver<>();
        set = new UniqueVault<>();

        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
            stack.push(i);
            set.add(i);
        }

        scanner = new LabScanner();
        sniper = new LabSniper();
    }
    @Test
    void scannerQueueSearchTest() {
        assertEquals(4, scanner.searchQueue(queue, 5));
        assertEquals(-1, scanner.searchQueue(queue, 10)); // not in queue
    }

    @Test
    void scannerStackSearchTest() {
        assertEquals(4, scanner.searchStack(stack, 1)); // bottom of stack is 1
        assertEquals(0, scanner.searchStack(stack, 5)); // not in stack
    }

    @Test
    void scannerSetSearchTest() {
        assertTrue(scanner.searchSet(set, 3));
        assertFalse(scanner.searchSet(set, 10));
    }

    @Test
    void sniperQueueSearchTest() {
        assertEquals(0, sniper.searchQueue(queue, 1));
        assertEquals(4, sniper.searchQueue(queue, 5));
        assertEquals(-1, sniper.searchQueue(queue, 10)); // not in queue
    }

    @Test
    void sniperStackSearchTest() {
        assertEquals(4, sniper.searchStack(stack, 1)); // bottom of stack is 1
        assertEquals(-1, sniper.searchStack(stack, 10)); // not in stack
    }

    @Test
    void sniperSetSearchTest() {
        assertTrue(sniper.searchSet(set, 3));
        assertFalse(sniper.searchSet(set, 10));
    }

    @Test
    void compareAllPerformance() {
        int size = 10000;

        // Original datasets
        FlowMaster<Integer> origQueue = new FlowMaster<>();
        PileDriver<Integer> origStack = new PileDriver<>();
        UniqueVault<Integer> set = new UniqueVault<>();

        for (int i = size; i > 0; i--) {
            origQueue.enqueue(i);
            origStack.push(i);
            set.add(i);
        }

        LabScanner scanner = new LabScanner();
        LabSniper sniper = new LabSniper();

        System.out.println("=== Queue Performance ===");
        // Use copies for lambda
        FlowMaster<Integer> queueForScanner = copyQueue(origQueue);
        System.out.println("Scanner: " + measureTime(() -> scanner.searchQueue(queueForScanner, size/2)) + " ns");

        FlowMaster<Integer> queueForSniper = copyQueue(origQueue);
        System.out.println("Sniper: " + measureTime(() -> sniper.searchQueue(queueForSniper, size/2)) + " ns");

        System.out.println("=== Stack Performance ===");
        PileDriver<Integer> stackForScanner = copyStack(origStack);
        System.out.println("Scanner: " + measureTime(() -> scanner.searchStack(stackForScanner, size/2)) + " ns");

        PileDriver<Integer> stackForSniper = copyStack(origStack);
        System.out.println("Sniper: " + measureTime(() -> sniper.searchStack(stackForSniper, size/2)) + " ns");

        System.out.println("=== Set Performance ===");
        System.out.println("Scanner: N/A (not used)");
        System.out.println("Sniper: " + measureTime(() -> sniper.searchSet(set, size/2)) + " ns");
    }

    // Helper method
    private long measureTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }

    // Helper methods to copy structures
    private FlowMaster<Integer> copyQueue(FlowMaster<Integer> original) {
        FlowMaster<Integer> copy = new FlowMaster<>();
        for (int i = 0; i < original.size(); i++) {
            copy.enqueue(original.peekAt(i)); // Assuming you have peekAt for FlowMaster
        }
        return copy;
    }

    private PileDriver<Integer> copyStack(PileDriver<Integer> original) {
        PileDriver<Integer> copy = new PileDriver<>();
        for (int i = 0; i < original.size(); i++) {
            copy.push(original.peekAt(i)); // bottom-to-top order
        }
        return copy;
    }
}