package Source.UnitTests;
import Source.*;
import org.junit.Test;

import org.junit.Assert;

public class CollissionTest {
    //public static void main(String[] args){ runCollisionTests();}

    //@Test
  //  public static void runCollisionTests(){
        // Mario collisions

        //Mario mario = new Mario(0.4, 0.6  + Mario.getHalfHeight());
    @Test
    public void marioCollisionTests() {
        Floor floor = new Floor(0.4, 0.65);
        Barrel barrel = new Barrel(0.4, 0.6 + Floor.getHeight() + 0.025);
        boolean collision = CollisionDetector.checkMarioCollision(floor.getX(), floor.getY(), 0.01, 0.4);
        Assert.assertTrue("There's not been a collision with the floor", collision);
        collision = CollisionDetector.checkMarioCollision(barrel.getX(), barrel.getY(), 0.025, 0.025);
        Assert.assertTrue("There's not been a collision with a barrel", collision);
    }
    @Test
    public void closestFloorTest() {
        //getClosesFloorY
        Floor floor = new Floor(0.4, 0.65);
        double closest = CollisionDetector.getCLosestFloorY(0.45);
        boolean aux = closest == floor.getY();
        Assert.assertTrue("The closest was wrong", aux);
    }
    //Floor Collisions
    @Test
    public void floorCollisionTests() {
        //Barril que colisiona con floor
        Barrel barrel2 = new Barrel(0.4, 0.65);
        boolean collision2 = CollisionDetector.checkMarioCollision(barrel2.getX(), barrel2.getY(), 0.025, 0.025);
        Assert.assertTrue("There's not been a barrer-floor collision", collision2);
    }


    //Ladder collision
    @Test
    public void ladderCollisionTests() {
        Ladder ladder = new Ladder(0.4, 0.125);
        //mario que colisiona con ladder
        Mario mario2 = new Mario(0.4, 0.125);
        boolean collision3 = CollisionDetector.checkLaddersCollision(mario2.getX(), mario2.getY(), Mario.getHalfWidth());
        Assert.assertTrue("There's not been a Mario-Ladder collision", collision3);
    }
        
        //CollisionDetector.checkMarioCollision(peach.getX(), peach.getY(), 0.01, 0.015)


//    }
}
