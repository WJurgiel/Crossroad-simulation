package org.wjurgiel.crossroad.Traffic;

public class TrafficFailureStrategy implements ITrafficStrategy{
    @Override
    public void executeStep(TrafficManager trafficManager){
        // implementation
        System.out.println("Traffic failure strategy");
    }
}
