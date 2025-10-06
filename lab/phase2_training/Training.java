package phase2_training;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.TeamBuilding;
import phase1_teams.UniqueVault;

public class Training {
    public static void main(String[] args) {
        // Phase 1: Team Building
        System.out.println("--- Team Building ---");
        TeamBuilding.main(null);

        // Phase 2: Cleaning Crew
        System.out.println("--- Cleaning Crew Training ---");

        // Prepare data for cleaning
        FlowMaster<Integer> flowMaster = new FlowMaster<>();
        flowMaster.enqueue(5);
        flowMaster.enqueue(1);
        flowMaster.enqueue(4);
        flowMaster.enqueue(2);

        PileDriver<Integer> pileDriver = new PileDriver<>();
        pileDriver.push(8);
        pileDriver.push(3);
        pileDriver.push(9);
        pileDriver.push(1);

        UniqueVault<Integer> uniqueVault = new UniqueVault<>();
        uniqueVault.add(42);
        uniqueVault.add(7);
        uniqueVault.add(19);
        uniqueVault.add(3);

        SoapySquad bubble = new SoapySquad();

        System.out.println("--- SoapySquad ---");
        System.out.println("Flowmaster before: " + flowMaster);
        bubble.cleanQueue(flowMaster);
        System.out.println("Flowmaster after: " + flowMaster);

        System.out.println("PileDriver before: " + pileDriver);
        bubble.cleanStack(pileDriver);
        System.out.println("PileDriver after: " + pileDriver);

        System.out.println("UniqueVault before: " + uniqueVault);
        bubble.cleanSet(uniqueVault);
        System.out.println("UniqueVault after: " + uniqueVault);

        FoamMaster smartBubble = new FoamMaster();

        System.out.println("--- FoamMaster ---");
        System.out.println("Flowmaster before: " + flowMaster);
        smartBubble.smartCleanQueue(flowMaster);
        System.out.println("Flowmaster after: " + flowMaster);

        System.out.println("PileDriver before: " + pileDriver);
        smartBubble.smartCleanStack(pileDriver);
        System.out.println("PileDriver after: " + pileDriver);

        System.out.println("UniqueVault before: " + uniqueVault);
        smartBubble.smartCleanSet(uniqueVault);
        System.out.println("UniqueVault after: " + uniqueVault);
    }
}