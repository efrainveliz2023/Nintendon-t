package Source;

public class FireballStrategy implements AttackStrategy{

    List<Fireball> fireballList = new LinkedList<Fireball>();

    @Override
    public void performAttack(Mario mario) {
        fireballList.add(new Fireball(mario.getX(), mario.getY(), mario.getDirection()));
    }

    @Override
    public void Run() {
        for(int i = 0; i < fireballList.size(); i++){
            fireballList.get(i).Run();
            if(!fireballList.get(i).GetAlive()){
                fireballList.remove(i);
                i--;
            }
        }

    }

    @Override
    public void Clear() {
        fireballList = new LinkedList<Fireball>();
    }
}
