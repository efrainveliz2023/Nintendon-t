package Source.UnitTests;
import Source.Floor;
import Source.Barrel;
import Source.Mario;
import org.junit.Test;
import org.junit.Assert;


public class BarrelTest {
    public static void main(String[] args){ runBarrelTests();}


    @Test
    public static void runBarrelTests(){
        Floor floor = new Floor(0.4, 0.6);
        Barrel barrel = new Barrel(0.2, 0.6 + Floor.getHeight() +  0.025) ;


        //mario is collissioning to the barel
        Mario mario = new Mario(0.4, 0.6 + Floor.getHeight() + Mario.getHalfHeight());
        //mario is not collisionning
        Mario mario1 = new Mario(0.7, 0.6 + Floor.getHeight() + Mario.getHalfHeight());



    }
}
