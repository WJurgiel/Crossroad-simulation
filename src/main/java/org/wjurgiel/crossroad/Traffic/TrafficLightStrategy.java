package org.wjurgiel.crossroad.Traffic;

import java.util.Objects;

public class TrafficLightStrategy implements ITrafficStrategy{
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
        if(greenLightLane == Lanes.HORIZONTAL){
            if(!trafficManager.getCarQueue(Directions.EAST).isEmpty()){
                Objects.requireNonNull(trafficManager.getCarQueue(Directions.EAST).poll()).go();
            }
            if(!trafficManager.getCarQueue(Directions.WEST).isEmpty()){
                Objects.requireNonNull(trafficManager.getCarQueue(Directions.WEST).poll()).go();
            }
        } else if(greenLightLane == Lanes.VERTICAL){
            if(!trafficManager.getCarQueue(Directions.NORTH).isEmpty()){
                Objects.requireNonNull(trafficManager.getCarQueue(Directions.NORTH).poll()).go();
            }
            if(!trafficManager.getCarQueue(Directions.SOUTH).isEmpty()){
                Objects.requireNonNull(trafficManager.getCarQueue(Directions.SOUTH).poll()).go();
            }
        }



    }
}
