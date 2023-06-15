package Source.UnitTests;

import Resources.PennDraw;
import Source.Mario;

// Estrategia de ataque con Kamehameha
public class KamehamehaAttackStrategy implements AttackStrategy {
    @Override
    public void performAttack(Mario mario) {


            PennDraw.picture(mario.getX(), mario.getY(), "kamehameha.png", 60, 47);

        System.out.println("Mario lanza un Kamehameha!");
    }


}