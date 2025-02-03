package org.wjurgiel.crossroad.Traffic;

public class TrafficLightStrategy implements ITrafficStrategy{
    private final int START_TIME = 2;
    private int ticksToChange; // implement dynamic time handling!
    private Lanes greenLightLane;

    public TrafficLightStrategy(){
        ticksToChange = START_TIME;
        greenLightLane = Lanes.HORIZONTAL;
    }
    @Override
    public void executeStep(TrafficManager trafficManager){
        // implementation
        System.out.println("Traffic light strategy, to change: " + --ticksToChange);
        if(ticksToChange  <= 0){
            greenLightLane = (greenLightLane == Lanes.HORIZONTAL) ? Lanes.VERTICAL : Lanes.HORIZONTAL;
            ticksToChange = START_TIME;
            System.out.println("Changing the lights!");
        }


    }
}
