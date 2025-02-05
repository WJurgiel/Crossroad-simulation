package org.wjurgiel.crossroad.Traffic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrafficLightStrategy extends AbstractTrafficStrategy{
    private final int START_TIME = 2;
    private int ticksToChange; // implement dynamic time handling!
    private Lanes greenLightLane;
    private  LightsSystem lightsSystem;
    public TrafficLightStrategy(){
        lightsSystem = LightsSystem.getInstance();
        ticksToChange = START_TIME;
        greenLightLane = Lanes.HORIZONTAL;
        printGreenLightLane();
    }
    private void printGreenLightLane(){
        System.out.println("--Green light lane: " + greenLightLane.toString() + "--");
    }
    @Override
    public void executeStep(TrafficManager trafficManager) {
        List<String> carsLeft = new ArrayList<>();

        if(!lightsSystem.tick()){
            greenLightLane = (greenLightLane == Lanes.HORIZONTAL) ? Lanes.VERTICAL : Lanes.HORIZONTAL;
            System.out.println("--Green light lane: " + greenLightLane + "--");
            return;
        }
        if (greenLightLane == Lanes.HORIZONTAL){
            moveCar(trafficManager, Directions.WEST, carsLeft);
            moveCar(trafficManager, Directions.EAST, carsLeft);
        }
        else if (greenLightLane == Lanes.VERTICAL){
            moveCar(trafficManager, Directions.NORTH, carsLeft);
            moveCar(trafficManager, Directions.SOUTH, carsLeft);
        }
        try {
            commitToOutputFile(carsLeft);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
