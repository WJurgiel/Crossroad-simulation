package org.wjurgiel.crossroad.Commands;

import org.wjurgiel.crossroad.Traffic.TrafficManager;

public class StepCommand implements ICommand {
    @Override
    public void execute(TrafficManager trafficManager){
        trafficManager.step();
    }
}
