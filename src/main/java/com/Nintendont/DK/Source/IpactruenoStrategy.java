package com.Nintendont.DK.Source;


public class IpactruenoStrategy implements AttackStrategy, Observer{
    final int cooldown = 2;
    final int duration = 1;
    int counter = 2;
    boolean performed = false;
    double x, y;
    @Override
    public void upDate(int segundos) {
        counter++;
        if(counter > cooldown){
            performed = false;
            Tiempo.getInstance().removeObserver(this);
        }
    }

    @Override
    public void performAttack(Mario mario){
        if(!performed){
            performed = true;
            counter = 0;
            x = mario.getX();
            y = mario.getY();
            Tiempo.getInstance().registrerObserver(this);
        }
    }

    @Override
    public void Run(){
        if(performed){
            if(counter < duration) {
                CollisionDetector.checkBarrelCollisionAndKill(x, y, 0.01, 10);
                PennDraw.picture(x, y + 0.43f, "lightning.PNG", 30, 500);
            }
        }
    }

    @Override
    public void Clear(){
        counter = 2;
        performed = false;
    }
}


