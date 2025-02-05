package org.wjurgiel.crossroad.Traffic;
import org.wjurgiel.crossroad.Car.Car;
import org.wjurgiel.crossroad.Files.FileHandler;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public abstract class AbstractTrafficStrategy implements ITrafficStrategy {
    protected void moveCar(TrafficManager trafficManager, Directions direction, List<String> carsLeft){
        Queue<Car> car = trafficManager.getCarQueue(direction);
        if(!car.isEmpty()){
            carsLeft.add(car.peek().getName());
            Objects.requireNonNull(car.poll()).go();
        }
    }
    protected void commitToOutputFile(List<String> carsLeft) throws IOException {
        FileHandler.getInstance().writeOutput(carsLeft);
    }
}
