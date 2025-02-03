package org.wjurgiel.crossroad.Commands;

import org.wjurgiel.crossroad.Traffic.TrafficFailureStrategy;
import org.wjurgiel.crossroad.Traffic.TrafficLightStrategy;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

public class FixLightsCommand implements ICommand{
    @Override
    public void execute(TrafficManager trafficManager) {
        System.out.println("Lights are fixed - resetting timer");
        trafficManager.setStrategy(new TrafficLightStrategy());
    }
}
