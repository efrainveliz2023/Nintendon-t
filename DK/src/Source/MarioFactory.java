package Source;

public class MarioFactory {

    public enum Characters{
        Mario, Pikachu;
        public static Characters fromInteger(int x){
            switch(x){
                case 0:
                    return Mario;
                case 1:
                    return Pikachu;
            }
            return null;
        }
    }

    static Characters playerOne = Characters.Mario;

    static void SetCharacterOne(int character){
        playerOne = Characters.fromInteger(character);
    }

    public static Mario CreateMario(double x, double y){
        double vel, jump;
        String idleSprite;
        String[] movingSprites;
        String climbingSprite;
        String jumpingSprite;

        switch (playerOne){
            case Pikachu:
                vel = 0.04;
                jump = 0.009;
                idleSprite = "pikachuStand.png";
                movingSprites = new String[]{"pikachu1.png", "pikachu2.png"};
                climbingSprite = "climbingPikachu.png";
                jumpingSprite = "pikachuJump.png";
                break;
            default:
                vel = 0.02;
                jump = 0.013;
                idleSprite = "marioStand.png";
                movingSprites = new String[]{ "marioStand.png", "marioRun1.png", "marioRun2.png" };
                climbingSprite = "climbingMario.png";
                jumpingSprite = "marioRun2.png";
                break;
        }
        return new Mario(x, y, vel, jump, idleSprite, movingSprites, climbingSprite, jumpingSprite);
    }
}
