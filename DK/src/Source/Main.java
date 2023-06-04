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
            while (d != 'y' && d != 'n') {
                if (PennDraw.hasNextKeyTyped())
                    d = PennDraw.nextKeyTyped();
                if (d == 'n') return;
            }
        } //close loop for play again
    }
}