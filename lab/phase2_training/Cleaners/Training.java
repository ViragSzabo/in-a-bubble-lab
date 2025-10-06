package phase2_training.Cleaners;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.TeamBuilding;
import phase1_teams.UniqueVault;

public class Training {
    public static void main(String[] args) {
        System.out.println("--- Team Building ---");
        TeamBuilding.main(null);

        System.out.println("\n--- Cleaning Crew Training ---");

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

        CleaningTeam bubble = new SoapySquad();
        CleaningTeam smartBubble = new FoamMaster();

        PerformanceTrainer.trainTeam(bubble, flowMaster, pileDriver, uniqueVault);
        PerformanceTrainer.trainTeam(smartBubble, flowMaster, pileDriver, uniqueVault);

        System.out.println("\n🏁 Training Complete! 🧼");
        System.out.println("🧽 Soapy Squad showed solid consistency and bubbly charm!");
        System.out.println("🫧 Foam Master demonstrated refined technique and faster cleaning!");
    }
}