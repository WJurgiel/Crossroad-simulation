package org.wjurgiel.crossroad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.wjurgiel.crossroad.Commands.CommandQueueManager;
import org.wjurgiel.crossroad.Commands.ICommand;
import org.wjurgiel.crossroad.Files.FileHandler;
import org.wjurgiel.crossroad.Traffic.LightsSystem;
import org.wjurgiel.crossroad.Traffic.TrafficFailureStrategy;
import org.wjurgiel.crossroad.Traffic.TrafficLightStrategy;
import org.wjurgiel.crossroad.Traffic.TrafficManager;

import java.io.IOException;
import java.util.Queue;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        String filePath = "src/main/resources/org/wjurgiel/crossroad/test.json";
        FileHandler fileHandler = FileHandler.getInstance();

        TrafficManager trafficManager = TrafficManager.getInstance();
        TrafficLightStrategy trafficLightStrategy = new TrafficLightStrategy();
        trafficManager.setStrategy(trafficLightStrategy);

        CommandQueueManager commandQueueManager = CommandQueueManager.getInstance(
                fileHandler.readFileAsJSONArray(filePath),
                trafficManager);
        while(commandQueueManager.hasCommands()){
            commandQueueManager.executeNextCommand();
        }

        System.out.println(trafficManager);
    }
    public static void main(String[] args) {
        launch();
    }
}