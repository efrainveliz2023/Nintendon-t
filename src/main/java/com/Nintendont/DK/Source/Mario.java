package com.Nintendont.DK.Source;

/******************************************************************************
 *  Compilation:  javac main.java.com.Nintendont.DK.Source.Mario.java
 *  Execution:    java main.java.com.Nintendont.DK.Source.Mario (runs basic testing
 *  Dependencies: image files
 *
 *  Creates a main.java.com.Nintendont.DK.Source.Mario object for Donkey Kong Game. main.java.com.Nintendont.DK.Source.Mario can
 * move, fall, and stop depending on floors inputted. main.java.com.Nintendont.DK.Source.Mario can be checked
 * compared to ladders and floors and barrels objects. Used in World.java
 *
 * Authors: Ethan Terner, eterner, 206
 *          Max Grove, maxgrove, 205
 *
 ******************************************************************************/
public class Mario {

    //Variables seteadas al instanciar
    private double x;
    private boolean timerOn=false;
    private boolean pause=false;
    private static int Score=0;
    private static boolean powerUp=false;
    private static  char lastKeyPressed = 's';
    private double y;
    private double velX;
    private double velY = 0;
    private final double jumpVel;
    private final String idleSprite;
    private final String[] movingSprites;
    private final String climbingSprite;
    private final String jumpingSprite;
    private AttackStrategy attackStrategy;
    private AttackStrategy baseAttackStrategy;
    public boolean fireball;

    private static final double halfHeight = 0.025;
    private static final double halfWidth = 0.01;
    private boolean isAlive = true;
    int direction = 0;

    //direction tracker for animation
    int movingDir = 0;

    //jumping variable
    boolean jumping = false;

    //false if not climbing, true if climbing
    boolean climbing = false;
    //int climb to keep track of which climbing frame
    int climb = 0;

    /* Constructor: sets main.java.com.Nintendont.DK.Source.Mario's x and y location */
    public Mario(double x, double y) {
        this.x = x;
        this.y = y;
        velX = 0.02;
        jumpVel = 0.012;
        idleSprite = "marioStand.png";
        movingSprites = new String[]{ "marioStand.png", "marioRun1.png", "marioRun2.png" };
        climbingSprite = "climbingMario.png";
        jumpingSprite = "marioRun2.png";
        direction = 0;
        Score=0;
        baseAttackStrategy = new FireballStrategy();
    }

    public Mario(double x, double y, double velX, double jumpVel,
                 String idleSprite, String[] movingSprites, String climbingSprite, String jumpingSprite,
                 AttackStrategy poweUp)
    {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.jumpVel = jumpVel;
        this.idleSprite = idleSprite;
        this.movingSprites = movingSprites;
        this.climbingSprite = climbingSprite;
        this.jumpingSprite = jumpingSprite;
        this.baseAttackStrategy = poweUp;

        direction = 0;
    }

    /**
     * Descripcion: Revisa los Inputs y colisiones, y actualiza la posicion de mario
     */
    void Run(){
        if(attackStrategy != null){
            attackStrategy.Run();
        }

        checkPosition();

        boolean ladders = CollisionDetector.checkLaddersCollision(x, y, halfWidth);
        boolean floors = CollisionDetector.checkFloorsCollision(x, y, halfHeight);

        if (ladders && climbing) {
            drawClimbing(climb);
        } else {
            boolean facing = (direction == 1);
            if(!floors){
                drawJump(true);
            } else if(movingDir == 0){
                draw(facing);
            } else {
                drawMoving(movingDir, facing);
            }
        }

        if (PennDraw.hasNextKeyTyped()) {
            char dir = PennDraw.nextKeyTyped();
            if (dir == 'a') {
                //if is not in the ladder and on the floor move
                if (!(ladders && !floors)) {
                    moveLeft();
                    movingDir++;
                    climbing = false;
                    direction = 1;
                    lastKeyPressed='i';
                }
            } else if (dir == 'd') {
                //if mario is not in the ladder and on the floor
                if (!(ladders && !floors)) {
                    moveRight();
                    movingDir++;
                    climbing = false;
                    direction = 2;
                    lastKeyPressed='d';
                }
            } else {
                movingDir = 0;
            }
            if (dir == 'w') {
                //if mario is in ladder, move up ladder
                if (ladders) {
                    // System.out.println("we made it here");
                    climbing = true;
                    climb++;
                    moveUp();
                }
                //otherwise if he is on the floor, jump
                else if (floors) {
                    StdAudio.play("SFX/jump.wav");
                    climbing = false;
                    jumping = true;
                    jump();
                }
            } else if (dir == 's') {
                //if mario is in ladder and not on the floor, move down
                if (ladders && !floors) {
                    climbing = true;
                    climb--;
                    moveDown();
                }
            } else if(dir == 'f'){
                if(attackStrategy != null) {
                    attackStrategy.performAttack(this);
                }
            } else if(dir == 'p'){
                setPause(true);
            }
        }
        else {
            movingDir = 0;
        }
        //update mario's y position for jumping
        updateY();

        //checks colliding with ladders
        if(!floors && !ladders) {
            fall();
        }
        else if (getVelY() < 0.0) {
            stop();
        }
    }

    /**
     * @return double x
     */
    public double getX() {
        return x;
    }

    public void setPowerUp(boolean powerUp) {
        if(powerUp){
            attackStrategy = baseAttackStrategy;
        } else {
            attackStrategy.Clear();
            attackStrategy = null;
        }
    }
    public void addScore(int Score){
          this.Score+=Score;
    }
    public int getScore2(){
        return Score;
    }
    public void resetScore(){
        Score=0;
    }

    /**
     * @return double y
     */
    public double getY() {
        return y;
    }

    /**
     * @return double yVel
     */
    public double getVelY() {
        return velY;
    }

    /**
     * @return halfHeight
     */
    public static double getHalfHeight() {
        return halfHeight;
    }

    /**
     * @return halfWidth
     */
    public static double getHalfWidth(){
        return halfWidth;
    }

    /**
     * @return boolean isAlive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Descriptios: Setea isAlive en falso
     */
    public void Kill(){
        isAlive = false;
    }

    /** Description: increases x by factor of velX
     */
    public void moveRight() {
        x += velX;
    }

    /** Description: decreases mario's x by velX
     */
    public void moveLeft() {
        x -= velX;
    }

    /** Description: setea la posicion y
     * @param y nueva posicion y.
     */
    public void setY(double y) {
        this.y = y;
    }

    /** Description: dibuja a mario en una de las imagenes mirando a la izquierda
     * @param dir - en que frame de la animacion esta mario
     * @param facing - direccion en la que está mirando. True = izquierda.
     */
    public void drawMoving(int dir, boolean facing){
        int direction = -1;
        if(facing) direction = 1;

        PennDraw.picture(x, y + 0.01, movingSprites[dir % movingSprites.length], 35 * direction, 35);
    }

    /** Description: dibuja a mario quieto
     * @param facing - direccion: true izquierda, false derecha
     */
    public void draw(boolean facing) {
        if (facing) {
            PennDraw.picture(x, y, idleSprite, 35, 35);
        } else {
            PennDraw.picture(x, y, idleSprite, -35, 35);
        }
    }

    /** Description:dibuja a mario escalando
     * @param dir - frame en el que esta mario
     */
    public void drawClimbing(int dir) {
        if (dir % 2 == 0) {
            PennDraw.picture(x, y, climbingSprite, 35, 35);
        } else if (dir % 2 == 1) {
            PennDraw.picture(x, y, climbingSprite, -35, 35);
        }
    }

    /** Description: Dibuja a mario en el aire
     * @param  facing - direccion en la que esta mirando, true izquierda
     */
    public void drawJump(boolean facing) {
        if (facing) {
            PennDraw.picture(x, y, jumpingSprite, -35, 35);
        } else {
            PennDraw.picture(x, y, jumpingSprite, 35, 35);
        }
    }

    /** Description: updates marios y vel for jumping feature
     */
    public void updateY() {
        y += velY;
    }
    public void setPause(boolean pause){
        this.pause=pause;
    }

     public boolean getPause(){
        return pause;
     }
    /** Description: updates marios y vel for jumping feature
     */
    public void jump() {
        velY = jumpVel;
    }

    /** Description: updates marios y vel for jumping feature
     */
    public void fall() {
        velY -= 0.001;
    }

    /** Description: Frena la caida de mario y lo coloca encima del piso más cercano
     */
    public void stop() {
        double closest = CollisionDetector.getCLosestFloorY(y);
        y = closest + halfHeight + Floor.getHeight();
        velY = 0.0;
    }

    /* Description: Moves mario up */
    public void moveUp() {
        y += 0.015;
    }

    /* Description: Moves mario down */
    public void moveDown() {
        y -= 0.015;
    }

    /** Description: Revisa si Mario está en una posicion válida, no le permite escaparse por los laterales
     * Si Mario cae por debajo de la panmtalla lo mata.
     */
    public void checkPosition() {
        if (x > 0.97) {
            x = 0.97;
        } else if (x < 0.03) {
            x = 0.03;
        }

        if (y < -0.05) {
            isAlive = false;
        }
    }

    public char getLastKeyPressed(){
        return lastKeyPressed;
    }

    public int getDirection(){ return direction; }
/*
    //TESTING: Ya no sirve por cambios en las funciones usadas.
    public static void main(String[] args) {
        Floor[] floor = new Floor[2];
        floor[0] = new Floor(0.6, 0.25);
        floor[1] = new Floor(0.4, 0.1);

        Mario mario = new Mario(0.5, floor[0].getY() + floor[0].getHeight()
                + 0.024); //start at 0.11 for good position

        PennDraw.enableAnimation(30);

        while(true) { //later make a boolean for if the game is running
            PennDraw.clear(PennDraw.WHITE);

            for (int i = 0; i < floor.length; i++ ) {
                floor[i].draw();
            }

            //checks for left or right movement
            if (PennDraw.hasNextKeyTyped()) {
                char dir = PennDraw.nextKeyTyped();
                if (dir == 'a') {
                    mario.moveLeft();
                }
                if (dir == 'd') {
                    mario.moveRight();
                }
                if (dir == 'w') {
                    //if (mario.floorCollision(floor))
                        //mario.jump();
                }
            }

            int counter = 0;
            for (int i = 0; i < floor.length; i++) {
                if ((floor[i].collision(mario))) {
                    counter++;
                    //   System.out.println("colliding");
                }
            }
            if(counter <= 0.0) {
                // System.out.println("he is not colliding with anything");
                mario.fall();
            } else if (mario.velY < 0.0) {
                // mario.stop(floors);
            }

            mario.updateY();
            PennDraw.advance();
        }
    }
    */
}