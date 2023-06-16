package Source;

import Resources.PennDraw;

public class Fireball {
    private double velX = 0.020;
    private char direccion;

    int velocity = 180;
    private int floorLevel = 0;
    private double x;
    private double y;
    public Fireball(double x, double y,char direccion) {
        this.x = x;
        this.y = y;
        this.direccion=direccion;
    }
    public void Run(Mario mario) {
      //  boolean barrelDestroyed = CollisionDetector.checkFloorsCollision(x, y);
         if(direccion=='s'){
             rollRight();
         }
         else if (direccion=='i'){
             rollLeft();
         }else{
            rollRight();
        }


        draw();
    }
    public void rollLeft() {
        x -= velX;
    }

    public void draw() {
        PennDraw.picture(x, y, "fireball.png", 24, 24);
    }
    public void rollRight() {
        x += velX;
    }
    public double getX(){

        return x;
    }
    public double getY(){

        return y;
    }

}
