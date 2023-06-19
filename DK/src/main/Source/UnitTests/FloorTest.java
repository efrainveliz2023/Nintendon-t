package main.Source.UnitTests;
import main.Source.Floor;
import main.Source.Barrel;
import main.Source.Mario;

public class FloorTest {
    public static void main(String[] args) {
        runFloorTests();
    }

    public static void runFloorTests(){
        Floor floor = new Floor(0.4, 0.6);
        assert (floor.getX() == 0.4) : "Unexpected floor spawn position";
        assert (floor.getY() == 0.6) : "Unexpected floor spawn position";
        //floor.draw();

        //barrel is on the top of the floor
        //barrel1 is avobe the floor, not colliding
        //2 & 3 are on the top left and rigth corner, should be detected
        //4 is out on the x axis, shoudnt be detected
        //5 & 6 borders on the rigth and left sides
        //7 is under, shouldn't be detected
        Barrel barrel = new Barrel(0.4, 0.6 + Floor.getHeight() + Barrel.getRadius());
        Barrel barrel1 = new Barrel(0.4, 0.6 + Floor.getHeight() + Barrel.getRadius() + 0.0001);
        Barrel barrel2 = new Barrel(0.4 + Floor.getWidth(),0.6 + Floor.getHeight() + Barrel.getRadius());
        Barrel barrel3 = new Barrel(0.4 - Floor.getWidth(),0.6 + Floor.getHeight() + Barrel.getRadius());
        Barrel barrel4 = new Barrel(0.4 + Floor.getWidth() + 0.0001,0.6 + Floor.getHeight() + Barrel.getRadius());
        Barrel barrel5 = new Barrel(0.4 + Floor.getWidth(),0.6);
        Barrel barrel6 = new Barrel(0.4 - Floor.getWidth(),0.6);
        Barrel barrel7 = new Barrel(0.4,0.6 - Floor.getHeight());
/*
        assert (floor.collision(barrel)) : "Floor/Barrel collision error detected";
        assert (!floor.collision(barrel1)) : "Floor/Barrel collision error detected";
        assert (floor.collision(barrel2)) : "Floor/Barrel collision error detected";
        assert (floor.collision(barrel3)) : "Floor/Barrel collision error detected";
        assert (!floor.collision(barrel4)) : "Floor/Barrel collision error detected";
        assert (floor.collision(barrel5)) : "Floor/Barrel collision error detected";
        assert (floor.collision(barrel6)) : "Floor/Barrel collision error detected";
        assert (!floor.collision(barrel7)) : "Floor/Barrel collision error detected";
*/
        //mario is on the top of the floor
        //mario1 is avobe the floor, not colliding
        //2 & 3 are on the top left and rigth corner, should be detected
        //4 is out on the x axis, shoudnt be detected
        //5 & 6 borders on the rigth and left sides
        //7 is under, shouldn't be detected
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        Mario mario1 = new Mario(0.4, 0.6 + Floor.getHeight() + Barrel.getRadius() + 0.0001);
        Mario mario2 = new Mario(0.4 + Floor.getWidth(),0.6 + Floor.getHeight() + Barrel.getRadius());
        Mario mario3 = new Mario(0.4 - Floor.getWidth(),0.6 + Floor.getHeight() + Barrel.getRadius());
        Mario mario4 = new Mario(0.4 + Floor.getWidth() + 0.0001,0.6 + Floor.getHeight() + Barrel.getRadius());
        Mario mario5 = new Mario(0.4 + Floor.getWidth(),0.6);
        Mario mario6 = new Mario(0.4 - Floor.getWidth(),0.6);
        Mario mario7 = new Mario(0.4,0.6 - Floor.getHeight());
/*
        assert (floor.collision(mario)) : "Floor/Mario collision error detected";
        assert (!floor.collision(mario1)) : "Floor/Mario collision error detected";
        assert (floor.collision(mario2)) : "Floor/Mario collision error detected";
        assert (floor.collision(mario3)) : "Floor/Mario collision error detected";
        assert (!floor.collision(mario4)) : "Floor/Mario collision error detected";
        assert (floor.collision(mario5)) : "Floor/Mario collision error detected";
        assert (floor.collision(mario6)) : "Floor/Mario collision error detected";
        assert (!floor.collision(mario7)) : "Floor/Mario collision error detected";
 */
    }
}
