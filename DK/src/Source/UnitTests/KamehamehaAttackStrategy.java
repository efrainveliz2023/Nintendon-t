package Source.UnitTests;

import Resources.PennDraw;
import Source.*;

// Estrategia de ataque con Kamehameha
public class KamehamehaAttackStrategy implements AttackStrategy {

    LinkedList<Fireball> fireballs;
    int velocity = 180;
    @Override
    public void performAttack(Mario mario) {
        mario.setTrueFireball();
    }




}