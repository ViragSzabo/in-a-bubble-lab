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
    void comparePerformanceBetweenTeams() {

    }
}