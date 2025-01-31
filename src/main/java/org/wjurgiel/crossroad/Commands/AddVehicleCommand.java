package org.wjurgiel.crossroad.Commands;

import org.wjurgiel.crossroad.Car;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

import java.util.Vector;

public class AddVehicleCommand implements ICommand{
    private String carName, startRoad, endRoad;
    private float carXSpawnPos, carYSpawnPos;
    AddVehicleCommand(String carName, String startRoad, String endRoad) {
        this.carName = carName;
        this.startRoad = startRoad;
        this.endRoad = endRoad;

        // implement spawnPos logic
        this.carXSpawnPos = 0;
        this.carYSpawnPos = 0;
    }
    @Override
    public void execute(TrafficManager trafficManager){
        trafficManager.addVehicle(new Car(carName, startRoad, endRoad, carXSpawnPos, carYSpawnPos, 3));
    }
}
