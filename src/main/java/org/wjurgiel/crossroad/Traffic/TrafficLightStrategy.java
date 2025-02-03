package org.wjurgiel.crossroad.Traffic;

public class TrafficLightStrategy implements ITrafficStrategy{
    private int ticksToChange = 10; // implement dynamic time handling!
    @Override
    public void executeStep(TrafficManager trafficManager){
        // implementation
        System.out.println("Traffic light strategy, to change: " + --ticksToChange);
    }
}
