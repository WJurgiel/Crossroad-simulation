package org.wjurgiel.crossroad.Commands;

import org.wjurgiel.crossroad.Traffic.TrafficFailureStrategy;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

public class FailureCommand implements ICommand {
    @Override
    public void execute(TrafficManager trafficManager) {
        System.out.println("Lights failure! Switching to different strategy!");
        trafficManager.setStrategy(new TrafficFailureStrategy());
    }
}
