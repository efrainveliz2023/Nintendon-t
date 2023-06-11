package Source;

import Resources.PennDraw;
import Resources.StdAudio;

public class Main {
    public static void main(String[] args) {

        /*
        //Set window size to screen max size.
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        Resources.PennDraw.setCanvasSize(screenWidth, screenHeight);
        */

        //DRAWING THE INSTRUCTIONS **************************************
        PennDraw.clear();
        PennDraw.setFontSize(15);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(0.5, 0.8, "Instructions:");
        PennDraw.text(0.5, 0.7, "use 'a' to move left, 'd' for right,");
        PennDraw.text(0.5, 0.6, "'w' to jump, and 's' to move down.");
        PennDraw.text(0.5, 0.5, "Climb the ladders to get to Princess Peach to win");
        PennDraw.text(0.5, 0.4, "Avoid the barrels - you lose if one hits you");
        PennDraw.text(0.5, 0.27, "Press 'f' to activate the special power up");
        PennDraw.setFontBold();
        PennDraw.text(0.5, 0.1, "Press 'y' to start the game");

        //waits until user presses 'y' to begin the game
        char c = 0;
        while (c != 'y') {
            if (PennDraw.hasNextKeyTyped())
                c = PennDraw.nextKeyTyped();
        }
        //***************************************************************

        //DRAWING THE CHARACTER SELECTOR **************************************
        PennDraw.text(0.5, 0.7, "use 'w' to move up, 's' for right,");
        PennDraw.text(0.5, 0.6, "'w' to jump, and 's' to move down.");
        PennDraw.text(0.5, 0.5, "Climb the ladders to get to Princess Peach to win");
        PennDraw.text(0.5, 0.4, "Avoid the barrels - you lose if one hits you");
        PennDraw.text(0.5, 0.27, "Press 'f' to activate the special power up");
        PennDraw.setFontBold();
        PennDraw.text(0.5, 0.1, "Press 'y' to start the game");

        //waits until user presses 'y' to begin the game
        int selectPos = 0;
        boolean selected = false;
        while (!selected) {

            PennDraw.clear();
            PennDraw.text(0.5, 0.8, "Choose your character:");
            PennDraw.text(0.5, 0.7, "Mario");
            PennDraw.text(0.5, 0.6, "Pikachu");
            PennDraw.text(0.35, 0.7 - 0.1 * selectPos, ">>");
            PennDraw.text(0.5, 0.1, "Press 'y' to select");
            while(true){
                if (PennDraw.hasNextKeyTyped()) {
                    c = PennDraw.nextKeyTyped();
                    switch(c){
                        case 'w':
                            selectPos--;
                            if(selectPos < 0) selectPos = 0;
                            break;
                        case 's':
                            selectPos++;
                            if(selectPos > 1) selectPos = 1;
                            break;
                        case 'y':
                            selected = true;
                            break;
                    }
                    break;
                }
            }
        }
        MarioFactory.SetCharacterOne(selectPos);
        //***************************************************************

        //begin playing background music immediately
        StdAudio.loop("SFX/bacmusic.wav");

        boolean playAgain = true;
        Levels actualLevel;
        while (playAgain) {
            //CHANGE ANIMATION SPEED FPS IF NEEDED
            PennDraw.enableAnimation(30);

            actualLevel = new Level_1();
            actualLevel.RunGameplayLoop();

            //function to see if user wants to play again************
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.setFontSize(25);
            PennDraw.text(0.5, 0.4, "Press 'y' to play again or 'n' to not");

            char d = 0;
            while (d != 'y') {
                if (PennDraw.hasNextKeyTyped())
                    d = PennDraw.nextKeyTyped();
                if (d == 'n') {
                    playAgain = false;
                    break;
                }
            }
        } //close loop for play again
    }
}