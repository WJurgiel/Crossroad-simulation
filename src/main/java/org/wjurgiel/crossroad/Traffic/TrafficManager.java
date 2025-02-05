package org.wjurgiel.crossroad.Traffic;

import org.wjurgiel.crossroad.Car.Car;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TrafficManager{
    private static TrafficManager instance;
    private final Map<Directions, Queue<Car>> cars = new HashMap<>();
    private ITrafficStrategy strategy;
    public TrafficManager(ITrafficStrategy strategy){
        for(Directions d : Directions.values()){
            cars.put(d, new LinkedList<>());
        }
        this.strategy = strategy;
    }
    public static TrafficManager getInstance(ITrafficStrategy strategy){
        if(instance == null){
            instance = new TrafficManager(strategy);
        }
        return instance;
    }
    public static TrafficManager getInstance(){
        if(instance == null){
            instance = new TrafficManager(null);
        }
        return instance;
    }
    public static void deleteInstance(){
        instance = null;
    }
    public Queue<Car> getCarQueue(Directions d){
        return cars.get(d);
    }
    public ITrafficStrategy getStrategy(){
        return strategy;
    }
    public void setStrategy(ITrafficStrategy strategy){
        this.strategy = strategy;
    }
    public void addVehicle(Directions direction,Car car){
        System.out.println("Traffic manager added new car: " + car);
        cars.get(direction).add(car);
    }
    public void step(){
        strategy.executeStep(this);
    }
    @Override
    public String toString(){
        return cars.toString();
    }
}
