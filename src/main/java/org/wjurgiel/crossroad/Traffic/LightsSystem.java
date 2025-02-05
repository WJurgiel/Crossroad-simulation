package org.wjurgiel.crossroad.Traffic;
import org.wjurgiel.crossroad.Car.Car;
import java.util.Queue;

public class LightsSystem {
    private static LightsSystem instance;
    private int ticksToChange;
    Queue<Car> eastCars, westCars, northCars, southCars;

    private LightsSystem(){
        TrafficManager trafficManager = TrafficManager.getInstance(TrafficManager.getInstance().getStrategy());
        eastCars = trafficManager.getCarQueue(Directions.EAST);
        westCars = trafficManager.getCarQueue(Directions.WEST);
        northCars = trafficManager.getCarQueue(Directions.NORTH);
        southCars = trafficManager.getCarQueue(Directions.SOUTH);

        generateStartTime();
    }
    public static LightsSystem getInstance (){
        if(instance == null){
            instance = new LightsSystem();
        }
        return instance;
    }
    public void deleteInstance(){
        instance = null;
    }
    public void generateStartTime(){
        int maxCarsOnVerticalLane = Math.max(northCars.size(),southCars.size());
        int maxCarsOnHorizontalLane = Math.max(eastCars.size(),westCars.size());

        if(maxCarsOnVerticalLane == 0 && maxCarsOnHorizontalLane == 0){
            ticksToChange = 2;
            System.out.println("Traffic lights: " + ticksToChange);
            return;
        }
        ticksToChange = Math.max(maxCarsOnHorizontalLane,maxCarsOnVerticalLane) + 1;
        System.out.println("Traffic lights: " + ticksToChange);
    }
    public boolean tick(){
        System.out.println("Traffic lights: " + --ticksToChange);
        return !hasTimeRunOut();
    }
    public int getTicksToChange(){
        return ticksToChange;
    }
    private boolean hasTimeRunOut(){
        if(ticksToChange <= 0){
            System.out.println("Changing the lights!");
            generateStartTime();
            return true;
        }
        return false;
    }

}
