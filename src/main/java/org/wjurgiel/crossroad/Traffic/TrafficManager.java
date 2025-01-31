package org.wjurgiel.crossroad.Traffic;

import org.wjurgiel.crossroad.Car;
import org.wjurgiel.crossroad.Commands.ICommand;

import java.util.LinkedList;
import java.util.Queue;

public class TrafficManager {
    private Queue<Car> cars = new LinkedList<Car>();
    public TrafficManager(){

    }

    public void addVehicle(Car car){
        // implementation
        System.out.println("Traffic manager added new car: " + car);
    }
    public void step(){
        // implementation
        System.out.println("Traffic manager step");
    }
}
