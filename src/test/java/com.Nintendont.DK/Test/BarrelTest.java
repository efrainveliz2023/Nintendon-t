package com.Nintendont.DK.Test;
import com.Nintendont.DK.Source.Floor;
import com.Nintendont.DK.Source.Barrel;
import com.Nintendont.DK.Source.Mario;
import org.junit.Test;
import org.junit.Assert;


public class BarrelTest {

    @Test
    public void runBarrelTests(){
        Floor floor = new Floor(0.4, 0.6);
        Barrel barrel = new Barrel(0.2, 0.6 + Floor.getHeight() +  0.025) ;

        //mario is collissioning to the barel
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        //mario is not collisionning
        Mario mario1 = new Mario(0.7, 0.6 + Floor.getHeight() + Mario.getHalfHeight());

    }
    @Test
    public void killBarrel(){
        Barrel barrel = new Barrel(0.2, 0.6 + Floor.getHeight() +  0.025) ;
        barrel.Kill();
        boolean alive = !barrel.isAlive();
        Assert.assertTrue("Barrel is not alive", alive);

    }
}
