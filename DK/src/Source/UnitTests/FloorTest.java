package Source.UnitTests;
import Source.Floor;
import Source.Barrel;

public class FloorTest {
    public static void main(String[] args) {
        FloorCreationTest();
        //FloorColisionTest();
    }

    public static void FloorCreationTest(){
        Floor floor[] = new Floor[1];
        floor[0] = new Floor(0.4, 0.6);
        assert (floor[0].getX() == 0.4) : "Unexpected floor spawn position";
        assert (floor[0].getY() == 0.6) : "Unexpected floor spawn position";
        floor[0].draw();

        //First barrel should detect a collision, while the second shouldn't
        Barrel barrel = new Barrel(0.4, 0.6 + 0.01 + 0.025);//center of floor + floor height + barrel radius
        Barrel barrel1 = new Barrel(0.4, 0.6 + 0.01 + 0.025 + 0.0001);//center of floor + floor height + barrel radius + 0.0001
        barrel.draw();

        assert (barrel.floorCollision(floor)) : "Unexpected floor/barrel colision error. Collision not working";
        assert (!barrel1.floorCollision(floor)) : "Unexpected floor/barrel colision error. Collision shoudn't exist";
    }
}
