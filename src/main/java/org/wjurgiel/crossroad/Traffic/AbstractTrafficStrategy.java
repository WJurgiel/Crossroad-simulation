package org.wjurgiel.crossroad.Traffic;
import org.wjurgiel.crossroad.Car.Car;
import java.util.Queue;

public abstract class AbstractTrafficStrategy implements ITrafficStrategy {
    protected void moveCar(TrafficManager trafficManager, Directions direction){
        Queue<Car> car = trafficManager.getCarQueue(direction);
        if(!car.isEmpty()){
            car.poll().go();
        }
    }
}
