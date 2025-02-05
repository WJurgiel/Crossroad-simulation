package org.wjurgiel.crossroad.Traffic;


import org.wjurgiel.crossroad.Car.Car;
import org.wjurgiel.crossroad.Car.Turning;
import org.wjurgiel.crossroad.Files.FileHandler;

import java.io.IOException;
import java.util.*;

public class TrafficFailureStrategy extends AbstractTrafficStrategy{
    private Lanes priorityLane;
    Map<Directions, Queue<Car>> priorityCarsQueue = new LinkedHashMap<>(2,0.75f,true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<Directions, Queue<Car>> eldest){
            return size()>2;
        }
    };
    Map<Directions, Queue<Car>> yieldCarsQueue = new LinkedHashMap<>(2,0.75f,true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<Directions, Queue<Car>> eldest){
            return size()>2;
        }
    };
    public TrafficFailureStrategy(Lanes priorityLane){
        this.priorityLane = priorityLane;
        System.out.println("--Priority lane - " + priorityLane.toString());
    }
    private void initializeLocalRefCarsQueue(TrafficManager trafficManager){
        if(priorityLane == Lanes.HORIZONTAL){
            priorityCarsQueue.put(Directions.EAST, trafficManager.getCarQueue(Directions.EAST));
            priorityCarsQueue.put(Directions.WEST, trafficManager.getCarQueue(Directions.WEST));

            yieldCarsQueue.put(Directions.NORTH, trafficManager.getCarQueue(Directions.NORTH));
            yieldCarsQueue.put(Directions.SOUTH, trafficManager.getCarQueue(Directions.SOUTH));
        }
        else if (priorityLane == Lanes.VERTICAL){
            priorityCarsQueue.put(Directions.NORTH, trafficManager.getCarQueue(Directions.NORTH));
            priorityCarsQueue.put(Directions.SOUTH, trafficManager.getCarQueue(Directions.SOUTH));

            yieldCarsQueue.put(Directions.EAST, trafficManager.getCarQueue(Directions.EAST));
            yieldCarsQueue.put(Directions.WEST, trafficManager.getCarQueue(Directions.WEST));
        }
    }
    private void moveYieldCar(Car yieldCar, TrafficManager trafficManager, List<String> carsLeft){
        if(yieldCar != null){
            Iterator<Map.Entry<Directions, Queue<Car>>> iterator = priorityCarsQueue.entrySet().iterator();
            Map.Entry<Directions, Queue<Car>> firstPriorityLane = iterator.hasNext() ? iterator.next() : null;
            Map.Entry<Directions, Queue<Car>> secondPriorityLane = iterator.hasNext() ? iterator.next() : null;
            Queue<Car> carOnTheLeft = priorityCarsQueue.get(yieldCar.getOnMyLeftDirection());
            Queue<Car> carOnTheRight = priorityCarsQueue.get(yieldCar.getOnMyRightDirection());
//            if(firstPriorityLane == null || secondPriorityLane == null)
//                throw new Exception("Cannot find first or second priority lane");
            if((yieldCar.getTurning() == Turning.FORWARD || yieldCar.getTurning() == Turning.LEFT)){
                if(priorityCarsQueue.get(Objects.requireNonNull(firstPriorityLane).getKey()).isEmpty() && priorityCarsQueue.get(Objects.requireNonNull(secondPriorityLane).getKey()).isEmpty()){
                    moveCar(trafficManager, yieldCar.getStartDirection(), carsLeft);
                }
                // coś tutaj
                else if (Objects.requireNonNull(carOnTheLeft.peek()).getTurning() == Turning.RIGHT && carOnTheRight.isEmpty()){
                    moveCar(trafficManager, yieldCar.getStartDirection(), carsLeft);
                }
            }
            else if (yieldCar.getTurning() == Turning.RIGHT){
                if(carOnTheLeft.isEmpty() || carOnTheLeft.peek().getTurning() == Turning.RIGHT){
                    moveCar(trafficManager, yieldCar.getStartDirection(), carsLeft);
                }
            }

        }
    }
    @Override
    public void executeStep(TrafficManager trafficManager) {
        List<String> carsLeft = new ArrayList<>();
        if(priorityCarsQueue.isEmpty()){
            initializeLocalRefCarsQueue(trafficManager);
        }
        System.out.println("STEP: Traffic failure strategy");
        if (priorityLane == Lanes.HORIZONTAL){
            Car carOnNorth = trafficManager.getCarQueue(Directions.NORTH).peek();
            Car carOnSouth = trafficManager.getCarQueue(Directions.SOUTH).peek();

            moveYieldCar(carOnNorth, trafficManager,carsLeft);
            moveYieldCar(carOnSouth, trafficManager,carsLeft);

            moveCar(trafficManager, Directions.WEST, carsLeft);
            moveCar(trafficManager, Directions.EAST, carsLeft);
        }
        else if (priorityLane == Lanes.VERTICAL){
            Car carOnEast = yieldCarsQueue.get(Directions.EAST).peek();
            Car carOnWest = yieldCarsQueue.get(Directions.WEST).peek();

            moveYieldCar(carOnEast, trafficManager, carsLeft);
            moveYieldCar(carOnWest, trafficManager, carsLeft);

            moveCar(trafficManager, Directions.NORTH,carsLeft);
            moveCar(trafficManager, Directions.SOUTH,carsLeft);
        }
        try {
            commitToOutputFile(carsLeft);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
