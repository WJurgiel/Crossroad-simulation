package org.wjurgiel.crossroad.Traffic;

import java.util.Objects;

public class TrafficLightStrategy extends AbstractTrafficStrategy{
    private final int START_TIME = 2;
    private int ticksToChange; // implement dynamic time handling!
    private Lanes greenLightLane;

    public TrafficLightStrategy(){
        ticksToChange = START_TIME;
        greenLightLane = Lanes.HORIZONTAL;
        printGreenLightLane();
    }
    private void printGreenLightLane(){
        System.out.println("--Green light lane: " + greenLightLane.toString() + "--");
    }
    @Override
    public void executeStep(TrafficManager trafficManager){
        // implementation
        System.out.println("Traffic lights: " + --ticksToChange);
        if(ticksToChange  <= 0){
            greenLightLane = (greenLightLane == Lanes.HORIZONTAL) ? Lanes.VERTICAL : Lanes.HORIZONTAL;
            ticksToChange = START_TIME;
            System.out.println("Changing the lights!");
            printGreenLightLane();
            return;
        }
        if (greenLightLane == Lanes.HORIZONTAL){
            moveCar(trafficManager, Directions.WEST);
            moveCar(trafficManager, Directions.EAST);
        }
        else if (greenLightLane == Lanes.VERTICAL){
            moveCar(trafficManager, Directions.NORTH);
            moveCar(trafficManager, Directions.SOUTH);
        }
    }
}
