package org.wjurgiel.crossroad;

import javafx.scene.image.Image;

public class Car {
    private Image image;
    private String name, from, to;
    private float xSpawnPoint, ySpawnPoint;
    private float xPos, yPos;
    private float speed;
    public Car(String name, String from, String to, float xSpawnPoint, float ySpawnPoint, float speed) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
    }
    public void go(){

    }
    public void waitForOther(){

    }
}
