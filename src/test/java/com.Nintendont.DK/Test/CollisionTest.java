package com.Nintendont.DK.Test;
import com.Nintendont.DK.Source.*;
import org.junit.Test;
import org.junit.Assert;
public class CollisionTest {
    protected Floor[] floors;
    Ladder[] ladders;
    @Test
    public void runCollisionTests(){
        floors = new Floor[1];
        Barrel barrel = new Barrel(0.2, 0.6 + Floor.getHeight() +  0.025) ;
            floors[0]=new Floor(0.4, 0.6);;




        //mario is collissioning to the barel
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        CollisionDetector collision = new CollisionDetector();
        collision.setFloors(floors);
        collision.setMario(mario);
        boolean floorTest = CollisionDetector.checkFloorsCollision(mario.getX(), mario.getY(), 0.025);
        Assert.assertTrue("Mario no esta en el piso", floorTest);


    }
    @Test
    public void runLadderTest(){


        ladders = new Ladder[1];
        ladders[0]=new Ladder(0.4, 0.6);;


        CollisionDetector collision = new CollisionDetector();
        collision.setLadders(ladders);
        Mario mario1 = new Mario(0.4, 0.5 + Floor.getHeight() + Mario.getHalfHeight());
        collision.setMario(mario1);
        boolean ladderTest = CollisionDetector.checkLaddersCollision(mario1.getX(), mario1.getY(), 0.025);
        Assert.assertTrue("Mario no esta en escalera", ladderTest);
    }

}
