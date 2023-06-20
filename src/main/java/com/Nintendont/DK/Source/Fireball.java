package com.Nintendont.DK.Source;


public class Fireball {
    private double velX = 0.020;
    private int direccion;

    int velocity = 180;
    private int floorLevel = 0;
    private double x;
    private double y;
    private boolean isAlive = true;
    public Fireball(double x, double y,int direccion) {
        this.x = x;
        this.y = y;
        this.direccion=direccion;
    }
    public void Run() {
      //  boolean barrelDestroyed = CollisionDetector.checkFloorsCollision(x, y);
         if(direccion==2){
             rollRight();
         }
         else if (direccion==1){
             rollLeft();
         }else{
            rollRight();
         }

         CollisionDetector.checkBarrelCollisionAndKill(x, y, 0.02f, 0.02f);

         checkPosition();

        draw();
    }
    public void checkPosition() {
        if (x > 1 || x < 0) {
            isAlive = false;
        }
    }
    public boolean GetAlive(){ return isAlive; }
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
