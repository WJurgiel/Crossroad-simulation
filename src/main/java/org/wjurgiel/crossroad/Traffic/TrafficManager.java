package org.wjurgiel.crossroad.Traffic;

import org.wjurgiel.crossroad.Car;
import org.wjurgiel.crossroad.Commands.ICommand;

import java.util.LinkedList;
import java.util.Queue;

public class TrafficManager{
    private static TrafficManager instance;
    private Queue<Car> cars = new LinkedList<Car>();
    private ITrafficStrategy strategy;
    public TrafficManager(ITrafficStrategy strategy){
        this.strategy = strategy;
    }
    public static TrafficManager getInstance(ITrafficStrategy strategy){
        if(instance == null){
            instance = new TrafficManager(strategy);
        }
        return instance;
    }
    public static void deleteInstance(){
        instance = null;
    }
    public void setStrategy(ITrafficStrategy strategy){
        this.strategy = strategy;
    }
    public void addVehicle(Car car){
        System.out.println("Traffic manager added new car: " + car);
        cars.add(car);
    }
    public void step(){
        strategy.executeStep(this);
    }
    @Override
    public String toString(){
        return cars.toString();
    }
}
