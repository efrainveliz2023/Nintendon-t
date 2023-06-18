package Source.UnitTests;

import Source.Floor;
import Source.Mario;
import org.junit.Test;

import org.junit.Assert;

public class MarioTest {
    //public static void main(String[] args){ runMarioTests();}

    //@Test
    //public void runMarioTests(){

    @Test
    public void marioAliveTest() {
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        //test is alive
        boolean alive = mario.isAlive();
        Assert.assertTrue("Mario is not alive", alive);
    }
    //
    //
    //


    @Test
    public void marioMoveDownTest(){
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
    double posY= mario.getY();

    mario.moveDown();
    boolean aux = posY > mario.getY();
    Assert.assertTrue("Mario did not moved down", aux);
}

    @Test
    public void marioMoveUpTest() {
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        double posY = mario.getY();
        mario.moveUp();
        boolean aux = posY > mario.getY();
        Assert.assertTrue("Mario did not moved up", aux);
    }

    @Test
    public void marioMoveLeftTest() {
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        double posX = mario.getX();
        mario.moveLeft();
        boolean aux = posX > mario.getX();
        Assert.assertTrue("Mario did not moved left", aux);
    }


    @Test
    public void marioMoveRightTest() {
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        double posX = mario.getX();
        mario.moveRight();
        boolean aux = posX < mario.getX();
        Assert.assertTrue("Mario did not moved right", aux);
    }
    //



    @Test
    public void marioOutOfRangeTest() {
        Mario mario1 = new Mario(0.4, 0.98);
        mario1.checkPosition();
        Assert.assertTrue("Mario went too far up!", mario1.isAlive());
    }



    @Test
    public void marioKillTest() {
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        ///////////////////////////////////////------------------------------------------
        mario.Kill();
        boolean alive = !mario.isAlive();
        Assert.assertTrue("Mario is not alive", alive);
    }


    //}
}
