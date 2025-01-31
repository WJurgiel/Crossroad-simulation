package org.wjurgiel.crossroad;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wjurgiel.crossroad.Commands.CommandQueueManager;
import org.wjurgiel.crossroad.Files.IFileReader;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
public class CommandQueueManagerTest {
    private TrafficManager mockTrafficManager;
    private CommandQueueManager commandQueueManager;

    @BeforeEach
    void setup(){
        mockTrafficManager = mock(TrafficManager.class);

        JSONArray fakeJsonCommandArray = new JSONArray();
        fakeJsonCommandArray.put(new JSONObject().put("type", "addVehicle")
                .put("vehicleId", "car1")
                .put("startRoad", "south")
                .put("endRoad", "north"));
        fakeJsonCommandArray.put(new JSONObject().put("type", "step"));
        fakeJsonCommandArray.put(new JSONObject().put("type", "step"));
        fakeJsonCommandArray.put(new JSONObject().put("type", "addVehicle")
                .put("vehicleId", "car2")
                .put("startRoad", "east")
                .put("endRoad", "west"));
        commandQueueManager = CommandQueueManager.getInstance(fakeJsonCommandArray, mockTrafficManager);
    }
    @AfterEach
    void teardown(){
        CommandQueueManager.deleteInstance();
    }
    @Test
    void commandQueue_InitializedCorrectly(){
        assertEquals(4, commandQueueManager.getCommandsCount(), "`Queue<ICommand> commands` in CommandQueueManager doesn't work correctly");
    }
    @Test
    void executeNextCommand_ExecutesStepCommandCorrectly(){
        commandQueueManager.executeNextCommand();
        commandQueueManager.executeNextCommand();
        commandQueueManager.executeNextCommand();

        verify(mockTrafficManager, times(2)).step();
    }
    @Test
    void executeNextCommand_ExecutesAddVehicleCommandCorrectly(){
        commandQueueManager.executeNextCommand();
        verify(mockTrafficManager, times(1)).addVehicle(any(Car.class));
    }
    @Test
    void executeNextCommand_DontExecuteOnEmptyQueue(){
        commandQueueManager.executeNextCommand();
        commandQueueManager.executeNextCommand();
        commandQueueManager.executeNextCommand();
        commandQueueManager.executeNextCommand();
        assertFalse(commandQueueManager.hasCommands(), "The `Queue<ICommand> commands` should be empty right now");

        commandQueueManager.executeNextCommand();

        verify(mockTrafficManager, times(2)).addVehicle(any(Car.class));
        verify(mockTrafficManager, times(2)).step();
    }
}
