package org.wjurgiel.crossroad.Commands;

import org.json.JSONArray;
import org.json.JSONObject;
import org.wjurgiel.crossroad.Traffic.Directions;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

import java.util.LinkedList;
import java.util.Queue;

public class CommandQueueManager {
    private static CommandQueueManager instance;
    private Queue<ICommand> _commands;
    private final TrafficManager _trafficManager;
    private CommandQueueManager(JSONArray commands, TrafficManager trafficManager) {
        // logger in the future
        initializeCommands(commands);
        this._trafficManager = trafficManager;
        System.out.println("All commands initialized!");
    }
    public static CommandQueueManager getInstance(JSONArray commands, TrafficManager trafficManager) {
        if(instance == null){
            instance = new CommandQueueManager(commands, trafficManager);
        }
        return instance;
    }
    public static void deleteInstance(){
        instance = null;
    }
    private void initializeCommands(JSONArray commandsArray){
        _commands = new LinkedList<ICommand>();
        for(int comm = 0; comm < commandsArray.length(); comm++){
            JSONObject command = commandsArray.getJSONObject(comm);
            String type = command.getString("type");
            if (type.equals("addVehicle")){
                String name = command.getString("vehicleId");
                String startRoad = command.getString("startRoad");
                String endRoad = command.getString("endRoad");
                Directions direction = (startRoad.equals("north")) ?
                        Directions.NORTH : (startRoad.equals("east") ?
                        Directions.EAST : (startRoad.equals("south") ?
                        Directions.SOUTH : Directions.WEST));
                _commands.add(new AddVehicleCommand(direction, name, startRoad, endRoad));
            }
            else if (type.equals("step")){
                _commands.add(new StepCommand());
            }
            else if (type.equals("failure")){
                _commands.add(new FailureCommand());
            }
            else if (type.equals("fix")){
                _commands.add(new FixLightsCommand());
            }
        }
    }
    public void executeNextCommand(){
        if(!_commands.isEmpty()){
            _commands.poll().execute(_trafficManager);
        }else{
            System.out.println("Nothing to do!");
        }
    }
    public int getCommandsCount(){
        return _commands.size();
    }
    public boolean hasCommands(){
        return !_commands.isEmpty();
    }


}
