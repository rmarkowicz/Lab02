package game;



import javafx.scene.Parent;

public class Ship extends Parent {
    public int type; //length of the ship
    public boolean isVertical = true;

    private int healthOfShip; //curent helath of the ship


    public Ship(int type, boolean vertical) {
        this.type = type;
        this.isVertical = vertical;
        healthOfShip = type;


    }

    //decreasing ship length if it's hit
    public void hit() {
        healthOfShip--;
    }

    public boolean isAlive() {

        return healthOfShip > 0;
    }
}