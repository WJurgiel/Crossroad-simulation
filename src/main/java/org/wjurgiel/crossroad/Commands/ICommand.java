package org.wjurgiel.crossroad.Commands;

import org.wjurgiel.crossroad.Traffic.TrafficManager;

public interface ICommand {
    void execute(TrafficManager trafficManager);
}
