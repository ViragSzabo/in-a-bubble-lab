package phase2_training.Cleaners;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import static org.junit.jupiter.api.Assertions.*;

class CleaningTeamTest {

    private FoamMaster foamMaster;
    private SoapySquad soapySquad;

    @BeforeEach
    void setUp() {
        foamMaster = new FoamMaster();
        soapySquad = new SoapySquad();
    }

    @Test
    void cleanQueueTest() {
        FlowMaster<Integer> queue = new FlowMaster<>();
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(9);
        queue.enqueue(1);

        foamMaster.cleanQueue(queue);
        assertEquals("1 -> 2 -> 5 -> 9 -> null", queue.toString());
    }

    @Test
    void cleanStackTest() {
        PileDriver<String> stack = new PileDriver<>();
        stack.push("delta");
        stack.push("alpha");
        stack.push("charlie");

        foamMaster.cleanStack(stack);
        assertTrue(stack.toString().contains("alpha"));
    }

    @Test
    void cleanSetTest() {
        UniqueVault<Double> vault = new UniqueVault<>();
        vault.add(3.14);
        vault.add(2.71);
        vault.add(1.41);

        foamMaster.cleanSet(vault);
        assertEquals("[1.41, 3.14, 2.71]", vault.toString());
    }

    @Test
    void compareCleaningPerformance() {
        int size = 10000;
        FlowMaster<Integer> q = buildQueue(size);
        PileDriver<Integer> s = buildStack(size);
        UniqueVault<Integer> v = buildSet(size);

        long foamQueue = measureTime(() -> foamMaster.cleanQueue(copyQueue(q)));
        long foamStack = measureTime(() -> foamMaster.cleanStack(copyStack(s)));
        long foamSet   = measureTime(() -> foamMaster.cleanSet(copySet(v)));

        long soapyQueue = measureTime(() -> soapySquad.cleanQueue(copyQueue(q)));
        long soapyStack = measureTime(() -> soapySquad.cleanStack(copyStack(s)));
        long soapySet   = measureTime(() -> soapySquad.cleanSet(copySet(v)));

        System.out.printf("Queue: Foam=%dns, Soapy=%dns%n", foamQueue, soapyQueue);
        System.out.printf("Stack: Foam=%dns, Soapy=%dns%n", foamStack, soapyStack);
        System.out.printf("Set: Foam=%dns, Soapy=%dns%n", foamSet, soapySet);
    }

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

    private UniqueVault<Integer> copySet(UniqueVault<Integer> original) {
        UniqueVault<Integer> copy = new UniqueVault<>();
        for (int i = 0; i < original.size(); i++) copy.add(original.getAt(i));
        return copy;
    }

    private long measureTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        return System.nanoTime() - start;
    }
}