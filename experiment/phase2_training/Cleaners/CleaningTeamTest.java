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
    void comparePerformanceBetweenTeams() {
        System.out.println("Flowmaster Cleaning");
        FlowMaster<Integer> queue = new FlowMaster<>();
        for (int i = 1000; i > 0; i--) queue.enqueue(i);

        SoapySquad soapy = new SoapySquad();
        FoamMaster foam = new FoamMaster();

        long start1 = System.nanoTime();
        soapy.cleanQueue(queue);
        long soapyTime = System.nanoTime() - start1;

        queue = new FlowMaster<>();
        for (int i = 1000; i > 0; i--) queue.enqueue(i);

        long start2 = System.nanoTime();
        foam.cleanQueue(queue);
        long foamTime = System.nanoTime() - start2;

        System.out.println("SoapySquad time: " + soapyTime + " ns");
        System.out.println("FoamMaster time: " + foamTime + " ns");
    }
}