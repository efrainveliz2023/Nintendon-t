package Source;
import Resources.PennDraw;
import Resources.StdAudio;
import javafx.scene.control.RadioMenuItem;

import java.util.Random;

public class Level_1 extends Levels implements Observer{
    private int amountFloor;

    public Level_1() {
        super();
        dificulty = 30;
        speedIncrease = 10;

    }

    @Override
    void SpawnLayout() {
        int amountFloor=getRandom();
        //Initialize floors***********************************************
        floors = new Floor[amountFloor]; //6 floors

        //initialize the floors alternating which edge they touch
        for (int i = 0; i < floors.length; i++) {
            if (i % 2 == 0) {
                floors[i] = new Floor(0.4, 0.8 - i * 0.15);
            }
            else {
                floors[i] = new Floor(0.6, 0.65 - (i - 1) * 0.15);
            }
        }

        collisions.setFloors(floors);
        //floors**********************************************************

        //initialize ladders**********************************************
        ladders = new Ladder[amountFloor-1];
        double Y=0.875;
        for(int i=amountFloor-2; i>=0;i--){
            Y=Y-0.150;
            Random random2=new Random();
            double x=random2.nextDouble() * 0.4+ 0.3;
            ladders[i] = new Ladder(x, Y);
        }


        collisions.setLadders(ladders);
        //ladders**********************************************************

        //set mario/peach/donkeykong to their starting points
        //TODO: El valor del character en Mario tiene que cambiar segun el selector de personajes.
        double lastFloorY= floors[(amountFloor-1)].getY() + Floor.getHeight() + 0.025;
        mario = MarioFactory.CreateMario(0.5, lastFloorY);
        peach = new Peach(0.70, floors[0].getY()
                + Floor.getHeight() + 0.035);
        donkey = new DonkeyKong(0.15, floors[0].getY()
                + Floor.getHeight() + 0.04);

        collisions.setMario(mario);

        star = new Star(floors[(amountFloor-1)].getY());
    }
    public int getRandom(){
        Random random=new Random();
        amountFloor= random.nextInt(4)+3;
        return amountFloor;
    }
}
