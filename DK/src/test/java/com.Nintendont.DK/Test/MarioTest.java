package test.java.com.Nintendont.DK.Test;

import main.java.com.Nintendont.DK.Source.Floor;
import main.java.com.Nintendont.DK.Source.Mario;
import org.junit.Test;

import org.junit.Assert;

public class MarioTest {
    public static void main(String[] args){ runMarioTests();}

    @Test
    public static void runMarioTests(){
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        //test is alive
        boolean alive = mario.isAlive();
        Assert.assertTrue("Mario is not alive", alive);
        //
        //
        //
        double posY= mario.getY();

        mario.moveDown();
        boolean aux = posY > mario.getY();
        Assert.assertTrue("Mario did not moved down", aux);

        posY= mario.getY();
        mario.moveUp();
        aux = posY > mario.getY();
        Assert.assertTrue("Mario did not moved up", aux);

        double posX= mario.getX();
        mario.moveLeft();
        aux= posX>mario.getX();
        Assert.assertTrue("Mario did not moved left", aux);

        posX= mario.getX();
        mario.moveRight();
        aux= posX<mario.getX();
        Assert.assertTrue("Mario did not moved right", aux);
        //
        Mario mario1 = new Mario ( 0.4, 0.98);
        mario1.checkPosition();
        Assert.assertTrue("Mario went too far up!", mario1.isAlive());




        ///////////////////////////////////////------------------------------------------
        mario.Kill();
        alive = !mario.isAlive();
        Assert.assertTrue("Mario is not alive", alive);


    }
}
