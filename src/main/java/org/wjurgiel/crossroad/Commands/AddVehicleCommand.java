package org.wjurgiel.crossroad.Commands;

import org.wjurgiel.crossroad.Car;
import org.wjurgiel.crossroad.Traffic.Directions;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

import java.util.Vector;

public class AddVehicleCommand implements ICommand{
    private String carName, startRoad, endRoad;
    private float carXSpawnPos, carYSpawnPos;
    private Directions direction;
    AddVehicleCommand(Directions direction, String carName, String startRoad, String endRoad) {
        this.direction = direction;

        this.carName = carName;
        this.startRoad = startRoad;
        this.endRoad = endRoad;

        // implement spawnPos logic
        this.carXSpawnPos = 0;
        this.carYSpawnPos = 0;
    }
    @Override
    public void execute(TrafficManager trafficManager){
        trafficManager.addVehicle(direction,new Car(carName, startRoad, endRoad, carXSpawnPos, carYSpawnPos, 3));
    }
}
