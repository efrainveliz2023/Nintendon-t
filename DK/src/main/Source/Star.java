package main.Source;

import main.Resources.PennDraw;

import java.util.Random;


public class Star implements Observer {
    private boolean noPowerUp=true;
    private double x;
    private double ymax;
    private double y;
    int deltaTiempo = 0;
    final int duracion = 10;
    Random random = new Random();

    public Star(){
        Tiempo.getInstance().registrerObserver(this);
    }

    public Star(double ymax){
        this.ymax=ymax;
        RandomPos();
        Tiempo.getInstance().registrerObserver(this);
    }

    void RandomPos(){
        x = random.nextDouble() * 0.6 + 0.2;
        y = random.nextDouble() *(0.9 - ymax) + ymax;
    }

    @Override
    public void upDate(int segundos) {
        if(!noPowerUp){
            deltaTiempo++;
        }
    }

    public void Run(Mario mario){
        if(noPowerUp) {
            draw();
            if (CollisionDetector.checkMarioCollision(x, y, 0.035, 0.035)) {
                mario.addScore(1000);
                mario.setPowerUp(true);
                noPowerUp = false;
            }
        } else {
            //System.out.println(deltaTiempo);
            if(deltaTiempo >= duracion){
                mario.setPowerUp(false);
                RandomPos();
                noPowerUp = true;
                deltaTiempo = 0;
            }
        }
    }

    public boolean isActive(){
        return !noPowerUp;
    }

    public void draw() {
        PennDraw.picture(x, y, "estrella.png", 40, 40);
    }
}
