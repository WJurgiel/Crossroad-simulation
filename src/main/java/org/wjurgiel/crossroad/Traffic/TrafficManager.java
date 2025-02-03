package org.wjurgiel.crossroad.Traffic;

import org.wjurgiel.crossroad.Car;
import org.wjurgiel.crossroad.Commands.ICommand;

import java.util.LinkedList;
import java.util.Queue;

public class TrafficManager {
    private Queue<Car> cars = new LinkedList<Car>();
    private ITrafficStrategy strategy;
    int ticksToChange = 10; // implement dynamic time handling!
    public TrafficManager(ITrafficStrategy strategy){
        this.strategy = strategy;
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
