package Source;

import Resources.PennDraw;


public class Star {
    private boolean noPowerUp=true;
    public void Run(double x, double y,Mario mario){
        if(noPowerUp)
        draw(x,y);
        if (CollisionDetector.checkStarCollision(x, y,0.025, 0.025)) {
            PennDraw.picture(-1, -1, "estrella.png", 40, 40);
            mario.setPowerUp(true);
            mario.setTimerOn(true);
            noPowerUp=false;
        }

    }
    public void draw(double x, double y) {
        PennDraw.picture(x, y, "estrella.png", 40, 40);

    }
    public void setNoPowerUp(){
        noPowerUp=true;

    }


}
