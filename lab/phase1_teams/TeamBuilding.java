package phase1_teams;

public class TeamBuilding {
    public static void main(String[] args) {
        System.out.println("--- QUEUE ---");
        FlowMaster<Integer> queue = new FlowMaster<>();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println("Queue peek: " + queue.peek()); // 1
        System.out.println("Queue dequeue: " + queue.dequeue()); // 1
        System.out.println("Queue size: " + queue.size()); // 1

        System.out.println("--- STACK ---");
        PileDriver<Integer> stack = new PileDriver<>();
        stack.push(10);
        stack.push(20);
        System.out.println("Stack pop: " + stack.pop()); // 20
        System.out.println("Stack size: " + stack.size()); // 1

        System.out.println("--- HASHSET ---");
        UniqueVault<Integer> set = new UniqueVault<>();
        set.add(5);
        set.add(5); // duplicate
        System.out.println("HashSet contains 5: " + set.contains(5)); // true
    }
}