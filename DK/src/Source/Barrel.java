package Source;

import Resources.PennDraw;

/******************************************************************************
 *  Compilation:  javac Source.Barrel.java
 *  Execution:    java Source.Barrel (runs basic testing)
 *  Dependencies: image files
 *
 *  Creates a barrel object for Donkey Kong Game. Source.Barrel can
 * roll, fall, and stop depending on floors inputted. Used in World.java
 *
 * Authors: Ethan Terner, eterner, 206
 *          Max Grove, maxgrove, 205
 *
 ******************************************************************************/
public class Barrel {
    private double x;
    private double y;
    private boolean killStatus=false;
    private double velX = 0.005;
    private double velY;
    private double fallVel = 0.001;
    private static final double radius = 0.025;
    private int floorLevel = 0;
    private int angle = 0;

    private boolean isAlive = true;

    /* Constructor: Creates barrel with x,y coordinates.*/
    public Barrel(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Revisa coliciones y actualiza la posicion
    public void Run(LinkedList<Fireball> fireballs){
        if(!killStatus) {
            boolean floors = CollisionDetector.checkFloorsCollision(x, y, radius);
            if (floors) {
                if (getFloorLevel() % 2 == 0) {
                    rollRight();
                } else {
                    rollLeft();
                }
            }

            floors = CollisionDetector.checkFloorsCollision(x, y, radius);

            if (!floors) {
                fall();
            } else if (getVelY() < 0.0) {
                int temp = getFloorLevel();
                setFloorLevel(temp + 1);
                stop();
            }

            updateY();
            draw();

            if (CollisionDetector.checkMarioCollision(x, y, radius, radius)) {
                CollisionDetector.KillMario();
            }

            if (CollisionDetector.checkFireballCollision(x, y, fireballs, 0.025, 0.025)) {
                killBarrell(this);
            }
        }if(killStatus){
            fallVel=0.01;
            fall();
            updateY();
            draw();


        }
    }


    public void checkPosition() {
        if(x < 0.03 || x > 0.97 || y < -0.05){
            isAlive = false;
        }
    }
    public void killBarrell(Barrel barrel){
        killStatus=true;
    }
    /**
     * Descripcion: Cambia la variable isALive a false
     */
    public void Kill(){
        isAlive = false;
    }

    public boolean GetAlive(){
        return isAlive;
    }

    /** Description: Draws a barrel at it's x and y location with a
     * constantly changing angle.
     */
    public void draw() {
        double neg = Math.pow(-1, floorLevel + 1);
        PennDraw.picture(x, y, "barrel.png", 24, 24, angle * neg);
        angle += 5;
    }

    /** Description: Draws 4 barrels in the top corner
     * @param floors for getting height to draw them at
     */
    public static void draw4(Floor[] floors) {
        double yHeight = floors[0].getY() + Floor.getHeight() + radius;
        PennDraw.picture(0.05, yHeight, "barrel.png", 24, 24);
        PennDraw.picture(0.05, yHeight + 2 * radius, "barrel.png", 24, 24);
        PennDraw.picture(0.10, yHeight, "barrel.png", 24, 24);
        PennDraw.picture(0.10, yHeight + 2 * radius, "barrel.png", 24, 24);
    }

    /** Description: changes the x position to the right
     */
    public void rollRight() {
        x += velX;
    }

    /** Description: changes the x position to the left
     */
    public void rollLeft() {
        x -= velX;
    }

    /** Description: returns x position
     */
    public double getX() {
        return x;
    }

    /** Description: returns y position
     */
    public double getY() {
        return y;
    }

    /** Description: gets floor level of barrel
     */
    public int getFloorLevel() {
        return floorLevel;
    }

    /** Description: sets floor level of barrel
     * @param floorLevel - which floorLevel to set at
     */
    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    /** Description: returns y velocity
     * @return velY
     */
    public double getVelY() {
        return velY;
    }

    /** Description: returns radius of barrels
     * @return double radius
     */
    public static double getRadius() {
        return radius;
    }

    /** Updates barrel's y position
     */
    public void updateY() {
        y += velY;
    }

    /** make the barrel fall by lowering y velocity
     */
    public void fall() { velY -= fallVel; }

    /** Stop the barrel from falling and put it at its floor level
     */
    public void stop() {
        double closest = CollisionDetector.getCLosestFloorY(y);
        y = closest + radius + Floor.getHeight();
        velY = 0.0;
    }

    //TESTING
    public static void main(String[] args) {
        Barrel b = new Barrel(0.5, 0.5);
        PennDraw.enableAnimation(30);

        while(true) {
            PennDraw.clear(PennDraw.WHITE);
            b.draw();
            b.fall();
            b.updateY();
            PennDraw.advance();
        }
    }
}