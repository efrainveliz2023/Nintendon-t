package com.Nintendont.DK.Source;

/******************************************************************************
 *  Compilation:  javac main.java.com.Nintendont.DK.Source.Peach.java
 *  Execution:    java main.java.com.Nintendont.DK.Source.Peach (runs basic testing
 *  Dependencies: image files
 *
 *  Creates a main.java.com.Nintendont.DK.Source.Peach object for Donkey Kong Game. main.java.com.Nintendont.DK.Source.Peach can
 * be drawn. Used in World.java
 *
 * Authors: Ethan Terner, eterner, 206
 *          Max Grove, maxgrove, 205
 *
 ******************************************************************************/
public class Peach {

    //FIELDS
    private double x;
    private double y;

    /* Constructor for peach: Set x and y
     *
     */
    public Peach(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /* Description: returns x location
     * @param n/a
     * @return double x
     */
    public double getX() {
        return x;
    }

    /* Description: returns y location
     * @param n/a
     * @return double y
     */
    public double getY() {
        return y;
    }

    /* Description: draw's peach at x, y
     * @param n/a
     * @return n/a
     */
    public void draw() {
        PennDraw.picture(x, y, "peach.png", -42, 38);
    }

    //testing
    public static void main(String[] args) {
        Peach peach = new Peach(0.25, 0.8 + 0.025);
        StdAudio.loop("SFX/bacmusic.wav");
        peach.draw();
    }
}