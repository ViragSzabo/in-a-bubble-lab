package phase1_teams;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FlowMasterTest {
    private FlowMaster flowMaster;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.flowMaster = new FlowMaster();
    }

    @org.junit.jupiter.api.Test
    void enqueue() {
        assertTrue(flowMaster.isEmpty());

        flowMaster.enqueue(1);
        flowMaster.enqueue(2);

        assertEquals(2, flowMaster.size());
        assertEquals(1, flowMaster.peek());
        assertEquals(1, flowMaster.dequeue());
        assertEquals(1, flowMaster.size());
        assertEquals(2, flowMaster.peek());
    }

    @org.junit.jupiter.api.Test
    void dequeueIfEmpty() {
        assertThrows(NoSuchElementException.class, flowMaster::dequeue);
    }
}