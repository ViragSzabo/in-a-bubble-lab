package phase4_subjects;

import phase1_teams.FlowMaster;
import phase1_teams.PileDriver;
import phase1_teams.UniqueVault;

import java.util.List;

public class Convert {

    public <T> FlowMaster<T> toQueue(List<T> data) {
        FlowMaster<T> flowMaster = new FlowMaster<>();
        data.forEach(flowMaster::enqueue);
        return flowMaster;
    }

    public <T> PileDriver<T> toStack(List<T> data) {
        PileDriver<T> pileDriver = new PileDriver<>();
        data.forEach(pileDriver::push);
        return pileDriver;
    }

    public <T> UniqueVault<T> toSet(List<T> data) {
        UniqueVault<T> uniqueVault = new UniqueVault<>();
        data.forEach(uniqueVault::add);
        return uniqueVault;
    }
}