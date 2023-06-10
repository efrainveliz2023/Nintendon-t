package Source;

public class MarioFactory {

    public enum Characters{
        Mario, Pikachu
    }

    public static Mario CreateMario(Characters c, double x, double y){
        double vel, jump;
        String idleSprite;
        String[] movingSprites;

        switch (c){
            case Pikachu:
                vel = 0.04;
                jump = 0.009;
                idleSprite = "pikachuStand.png";
                movingSprites = new String[]{"pikachu1.png", "pikachu2.png"};
                break;
            default:
                vel = 0.02;
                jump = 0.013;
                idleSprite = "marioStand.png";
                movingSprites = new String[]{ "marioStand.png", "marioRun1.png", "marioRun2.png" };
                break;
        }
        return new Mario(x, y, vel, jump, idleSprite, movingSprites);
    }
}
