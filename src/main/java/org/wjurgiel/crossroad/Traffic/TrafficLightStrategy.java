package org.wjurgiel.crossroad.Traffic;

public class TrafficLightStrategy implements ITrafficStrategy{
    @Override
    public void executeStep(TrafficManager trafficManager){
        // implementation
        System.out.println("Traffic light strategy");
    }
}
