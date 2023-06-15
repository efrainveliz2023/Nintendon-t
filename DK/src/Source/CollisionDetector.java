package Source;

public class CollisionDetector {
    private static Ladder[] ladders;
    private static Floor[] floors;
    private static Mario mario;

    public void setLadders(Ladder[] ladders) {
        CollisionDetector.ladders = ladders;
    }
    public void setFloors(Floor[] floors) {
        CollisionDetector.floors = floors;
    }
    public void setMario(Mario mario){
        CollisionDetector.mario = mario;
    }


    public static boolean checkLaddersCollision(double x, double y, double halfWidth){
        for (int i = 0; i < ladders.length; i++) {
            if(ladders[i].getX() - Ladder.getHalfWidth() < x + halfWidth && x - halfWidth < ladders[i].getX() + Ladder.getHalfWidth()) {
                if (ladders[i].getY() - Ladder.getHalfHeight() < y && y < ladders[i].getY() + Ladder.getHalfWidth()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkFloorsCollision(double x, double y, double halfHeight){
        for (int i = 0; i < floors.length; i++) {
            if ((y - halfHeight <= floors[i].getY() + Floor.getHeight()) && y >= floors[i].getY()) {
                if(x <= floors[i].getX() + Floor.getWidth() && x >= floors[i].getX() - Floor.getWidth()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static double getCLosestFloorY(double y){
        double min = Double.POSITIVE_INFINITY;
        double closest = 0;

        for (int i = 0; i < floors.length; i++) {
            double temp = Math.abs(y - floors[i].getY());
            if (temp < min) {
                closest = floors[i].getY();
                min = temp;
            }
        }

        return closest;
    }

    public static boolean checkMarioCollision(double x, double y, double halfWidth, double halfHeight){
        if (x - halfWidth < mario.getX() + Mario.getHalfWidth() && mario.getX() - Mario.getHalfWidth() < x + halfWidth) {
            if (y - halfHeight < mario.getY() + Mario.getHalfHeight() && mario.getY() - Mario.getHalfWidth() < y + halfHeight)
                return true;
        }
        return false;
    }

    public static void KillMario(){
        mario.Kill();
    }
}
