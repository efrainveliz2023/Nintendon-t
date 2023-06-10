package Source;
import Resources.PennDraw;
import Resources.StdAudio;

public class Level_1 extends Levels{



    @Override
    void SpawnLayout() {
        CollisionDetector collisions = new CollisionDetector();

        //Initialize floors***********************************************
        floors = new Floor[6]; //6 floors

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
        ladders = new Ladder[5];
        ladders[0] = new Ladder(0.4, 0.125);
        ladders[1] = new Ladder(0.7, 0.275);
        ladders[2] = new Ladder(0.3, 0.425);
        ladders[3] = new Ladder(0.6, 0.575);
        ladders[4] = new Ladder(0.45, 0.725);

        collisions.setLadders(ladders);
        //ladders**********************************************************

        //set mario/peach/donkeykong to their starting points
        //TODO: El valor del character en Mario tiene que cambiar segun el selector de personajes.
        mario = MarioFactory.CreateMario(MarioFactory.Characters.Mario, 0.5,
                floors[5].getY() + Floor.getHeight() + 0.025);

        peach = new Peach(0.70, floors[0].getY()
                + Floor.getHeight() + 0.035);
        donkey = new DonkeyKong(0.15, floors[0].getY()
                + Floor.getHeight() + 0.04);

        collisions.setMario(mario);
    }
}
