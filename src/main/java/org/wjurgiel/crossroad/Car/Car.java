package org.wjurgiel.crossroad.Car;

import javafx.scene.image.Image;
import org.wjurgiel.crossroad.Traffic.Directions;

public class Car {
    private Image image;
    private String name, from, to;
    private float xSpawnPoint, ySpawnPoint;
    private float xPos, yPos;
    private float speed;
    private Directions startDirection;
    private Directions endDirection;
    private Directions onMyLeftDirection;
    private Turning turning;
    public Car(String name, String from, String to, float xSpawnPoint, float ySpawnPoint, float speed) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.xSpawnPoint = xSpawnPoint;
        this.ySpawnPoint = ySpawnPoint;

        startDirection = setDirection(from);
        endDirection = setDirection(to);
        turning = setTurning();
        setOnMyLeftAndRight();

        this.xPos = xSpawnPoint;
        this.yPos = ySpawnPoint;
        this.speed = speed;
    }
    private Directions setDirection(String str){
        Directions dir = (str.equals("north")) ? Directions.NORTH :
                (str.equals("east")) ? Directions.EAST :
                        (str.equals("south")) ? Directions.SOUTH : Directions.WEST;
        return dir;
    }
    private void setOnMyLeftAndRight(){
        Directions[] values = Directions.values();
        int onMyLeftIndex = (startDirection.ordinal() + 1) % values.length;
        int onMyRightIndex = ((endDirection.ordinal() - 1) < 0) ? values.length - 1 : endDirection.ordinal() - 1;
        onMyLeftDirection = values[onMyLeftIndex];
    }
    private Turning setTurning(){
        int difference = Math.abs(startDirection.ordinal() - endDirection.ordinal());
        switch (difference){
            case 1:
                return Turning.RIGHT;
            case 2:
                return Turning.FORWARD;
            case 3:
                return Turning.LEFT;
        }
        return null;
    }
    public Turning getTurning(){
        return turning;
    }
    public Directions getOnMyLeftDirection(){
        return onMyLeftDirection;
    }
    public Directions getStartDirection(){
        return startDirection;
    }
    public void go(){
        System.out.println("!" + name + " exits the crossroad. Farewell driver!");
    }
    public void waitForOther(){

    }

    @Override
    public String toString() {
        return name + " " + startDirection.toString() + " -> " + endDirection.toString();
    }
}
