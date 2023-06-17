package Source;

import Resources.PennDraw;

import java.util.Random;


public class Star implements Observer {
    private boolean noPowerUp=true;
    private double x;
    private double y;
    int deltaTiempo = 0;
    final int duracion = 10;
    Random random = new Random();

    public Star(double x, double y){
        this.x = x;
        this.y = y;
        Tiempo.getInstance().registrerObserver(this);
    }

    public Star(){
        RandomPos();
        Tiempo.getInstance().registrerObserver(this);
    }

    void RandomPos(){
        x = random.nextDouble() * 0.6 + 0.2;
        y = random.nextDouble() * 0.8 + 0.1;
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
            if (CollisionDetector.checkMarioCollision(x, y, 0.025, 0.025)) {
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
