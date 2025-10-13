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
        assertEquals(4, scanner.searchQueue(queue, 5), "Scanner should find 5 at index 4");
        assertEquals(-1, scanner.searchQueue(queue, 10), "Scanner should not find 10");
    }

    @Test
    void scannerStackSearchTest() {
        assertEquals(4, scanner.searchStack(stack, 1), "Scanner should find bottom element 1");
        assertEquals(-1, scanner.searchStack(stack, 10), "Scanner should not find 10");
    }

    @Test
    void scannerSetSearchTest() {
        assertTrue(scanner.searchSet(set, 3), "Scanner should find 3 in set");
        assertFalse(scanner.searchSet(set, 10), "Scanner should not find 10 in set");
    }

    @Test
    void sniperQueueSearchTest() {
        assertEquals(0, sniper.searchQueue(queue, 1), "Sniper should find 1 at index 0");
        assertEquals(4, sniper.searchQueue(queue, 5), "Sniper should find 5 at index 4");
        assertEquals(-1, sniper.searchQueue(queue, 10), "Sniper should not find 10");
    }

    @Test
    void sniperStackSearchTest() {
        assertEquals(0, sniper.searchStack(stack, 1), "Sniper should find bottom element 1");
        assertEquals(-1, sniper.searchStack(stack, 10), "Sniper should not find 10");
    }

    @Test
    void sniperSetSearchTest() {
        assertTrue(sniper.searchSet(set, 3), "Sniper should find 3 in set");
        assertFalse(sniper.searchSet(set, 10), "Sniper should not find 10 in set");
    }

    @Test
    void compareAllPerformance() {
        int size = 10000;

        FlowMaster<Integer> origQueue = new FlowMaster<>();
        PileDriver<Integer> origStack = new PileDriver<>();
        UniqueVault<Integer> origSet = new UniqueVault<>();

        for (int i = size; i > 0; i--) {
            origQueue.enqueue(i);
            origStack.push(i);
            origSet.add(i);
        }

        // Test scanner vs sniper
        long queueScannerTime = measureTime(() -> scanner.searchQueue(copyQueue(origQueue), size/2));
        long queueSniperTime  = measureTime(() -> sniper.searchQueue(copyQueue(origQueue), size/2));
        long stackScannerTime = measureTime(() -> scanner.searchStack(copyStack(origStack), size/2));
        long stackSniperTime  = measureTime(() -> sniper.searchStack(copyStack(origStack), size/2));
        long setSniperTime    = measureTime(() -> sniper.searchSet(origSet, size/2));

        System.out.printf("Queue: Scanner=%dns, Sniper=%dns%n", queueScannerTime, queueSniperTime);
        System.out.printf("Stack: Scanner=%dns, Sniper=%dns%n", stackScannerTime, stackSniperTime);
        System.out.printf("Set: Sniper=%dns%n", setSniperTime);

        // Basic correctness assertions
        assertTrue(scanner.searchQueue(copyQueue(origQueue), size/2) >= 0);
        assertTrue(sniper.searchStack(copyStack(origStack), size/2) >= 0);
        assertTrue(sniper.searchSet(origSet, size/2));
    }

    private long measureTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }

    private FlowMaster<Integer> copyQueue(FlowMaster<Integer> original) {
        FlowMaster<Integer> copy = new FlowMaster<>();
        for (int i = 0; i < original.size(); i++) copy.enqueue(original.peekAt(i));
        return copy;
    }

    private PileDriver<Integer> copyStack(PileDriver<Integer> original) {
        PileDriver<Integer> copy = new PileDriver<>();
        for (int i = 0; i < original.size(); i++) copy.push(original.peekAt(i));
        return copy;
    }
}