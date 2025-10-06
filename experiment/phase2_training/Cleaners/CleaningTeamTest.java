package phase2_training.Cleaners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import static org.junit.jupiter.api.Assertions.*;

class CleaningTeamTest {
    private FoamMaster foamMaster;

    @BeforeEach
    void setUp() {
        this.foamMaster = new FoamMaster();
    }

    @Test
    void cleanQueue_withIntegers() {
        FlowMaster<Integer> queue = new FlowMaster<>();
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(9);
        queue.enqueue(1);

        foamMaster.cleanQueue(queue);

        assertEquals("1 -> 2 -> 5 -> 9 -> null", queue.toString());
    }

    @Test
    void cleanStack_withStrings() {
        PileDriver<String> stack = new PileDriver<>();
        stack.push("delta");
        stack.push("alpha");
        stack.push("charlie");

        foamMaster.cleanStack(stack);

        assertTrue(stack.toString().contains("alpha"), "Stack should be sorted lexicographically");
    }

    @Test
    void cleanSet_withMixedTypes() {
        UniqueVault<Double> vault = new UniqueVault<>();
        vault.add(3.14);
        vault.add(2.71);
        vault.add(1.41);

        foamMaster.cleanSet(vault);

        assertEquals("[1.41, 3.14, 2.71]", vault.toString());
    }

    @Test
    void compareIntegerSortingBetweenTeams() {
        FlowMaster<Integer> queue1 = new FlowMaster<>();
        FlowMaster<Integer> queue2 = new FlowMaster<>();

        queue1.enqueue(4);
        queue1.enqueue(1);
        queue1.enqueue(3);

        queue2.enqueue(4);
        queue2.enqueue(1);
        queue2.enqueue(3);

        SoapySquad soapy = new SoapySquad();
        FoamMaster foam = new FoamMaster();

        soapy.cleanQueue(queue1);
        foam.cleanQueue(queue2);

        assertEquals(queue1.toString(), queue2.toString(),
                "Both teams should produce identical cleaned results!");
    }

    @Test
    void cleanQueue_withNegativeNumbers() {
        FlowMaster<Integer> queue = new FlowMaster<>();
        queue.enqueue(-5);
        queue.enqueue(0);
        queue.enqueue(3);
        foamMaster.cleanQueue(queue);
        assertEquals("-5 -> 0 -> 3 -> null", queue.toString());
    }

    @Test
    void cleanStack_preservesSize() {
        PileDriver<String> stack = new PileDriver<>();
        stack.push("b");
        stack.push("a");
        int originalSize = stack.size();
        foamMaster.cleanStack(stack);
        assertEquals(originalSize, stack.size());
    }

    @Test
    void compareAllCleaningPerformance() {
        int size = 10000;

        SoapySquad soapy = new SoapySquad();
        FoamMaster foam = new FoamMaster();

        // Original datasets
        FlowMaster<Integer> origQueue = buildQueue(size);
        PileDriver<Integer> origStack = buildStack(size);
        UniqueVault<Integer> origSet = buildSet(size);

        System.out.println("=== Queue Cleaning Performance ===");
        // Queue performance
        long soapyQueueTime = measureTime(() -> soapy.cleanQueue(copyQueue(origQueue)));
        long foamQueueTime = measureTime(() -> foam.cleanQueue(copyQueue(origQueue)));
        System.out.println("SoapySquad: " + soapyQueueTime + " ns");
        System.out.println("FoamMaster: " + foamQueueTime + " ns");

        System.out.println("=== Stack Cleaning Performance ===");
        // Stack performance
        long soapyStackTime = measureTime(() -> soapy.cleanStack(copyStack(origStack)));
        long foamStackTime = measureTime(() -> foam.cleanStack(copyStack(origStack)));
        System.out.println("SoapySquad: " + soapyStackTime + " ns");
        System.out.println("FoamMaster: " + foamStackTime + " ns");

        System.out.println("=== Set Cleaning Performance ===");
        // Set performance
        long soapySetTime = measureTime(() -> soapy.cleanSet(copySet(origSet)));
        long foamSetTime = measureTime(() -> foam.cleanSet(copySet(origSet)));
        System.out.println("SoapySquad: " + soapySetTime + " ns");
        System.out.println("FoamMaster: " + foamSetTime + " ns");
    }

    // Helpers to build datasets
    private FlowMaster<Integer> buildQueue(int size) {
        FlowMaster<Integer> queue = new FlowMaster<>();
        for (int i = size; i > 0; i--) queue.enqueue(i);
        return queue;
    }

    private PileDriver<Integer> buildStack(int size) {
        PileDriver<Integer> stack = new PileDriver<>();
        for (int i = size; i > 0; i--) stack.push(i);
        return stack;
    }

    private UniqueVault<Integer> buildSet(int size) {
        UniqueVault<Integer> set = new UniqueVault<>();
        for (int i = size; i > 0; i--) set.add(i);
        return set;
    }

    // Helpers to copy datasets
    private FlowMaster<Integer> copyQueue(FlowMaster<Integer> original) {
        FlowMaster<Integer> copy = new FlowMaster<>();
        for (int i = 0; i < original.size(); i++) {
            copy.enqueue(original.peekAt(i));
        }
        return copy;
    }

    private PileDriver<Integer> copyStack(PileDriver<Integer> original) {
        PileDriver<Integer> copy = new PileDriver<>();
        for (int i = 0; i < original.size(); i++) {
            copy.push(original.peekAt(i));
        }
        return copy;
    }

    private UniqueVault<Integer> copySet(UniqueVault<Integer> original) {
        UniqueVault<Integer> copy = new UniqueVault<>();
        for (int i = 0; i < original.size(); i++) {
            copy.add(original.getAt(i));
        }
        return copy;
    }

    // Measure execution time
    private long measureTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }
}