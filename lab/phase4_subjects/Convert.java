package phase4_subjects;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.List;

public class Convert {
    public FlowMaster<String> toQueue(List<String> data) {
        FlowMaster<String> flowMaster = new FlowMaster<>();
        for (String s : data) {
            flowMaster.enqueue(s);
        }
        return flowMaster;
    }

    public PileDriver<String> toStack(List<String> data) {
        PileDriver<String> pileDriver = new PileDriver<>();
        for (String s : data) {
            pileDriver.push(s);
        }
        return pileDriver;
    }

    public UniqueVault<String> toSet(List<String> data) {
        UniqueVault<String> uniqueVault = new UniqueVault<>();
        for (String s : data) {
            uniqueVault.add(s);
        }
        return uniqueVault;
    }
}