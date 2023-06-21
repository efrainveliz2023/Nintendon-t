package com.Nintendont.DK.Test;
import com.Nintendont.DK.Source.*;
import org.junit.Test;
import org.junit.Assert;
public class CollisionTest {
    @Test
    public void runCollisionTests(){
        Floor floor = new Floor(0.4, 0.6);
        Barrel barrel = new Barrel(0.2, 0.6 + Floor.getHeight() +  0.025) ;


        //mario is collissioning to the barel
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());


        boolean floors = CollisionDetector.checkFloorsCollision(mario.getX(), mario.getY(), 0.025);
        Assert.assertTrue("Mario no esta en el piso", floors);


    }
    @Test
    public void runLadderTest(){
        Ladder lader=new Ladder(0.4,0.6);
        Mario mario1 = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        boolean ladder = CollisionDetector.checkLaddersCollision(mario1.getX(), mario1.getY(), 0.025);
        Assert.assertTrue("Mario no esta en escalera", ladder);
    }

}
