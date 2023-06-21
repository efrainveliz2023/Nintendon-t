package com.Nintendont.DK.Source;

import java.awt.*;
import java.util.Random;

public abstract class Levels implements Observer  {
    protected Floor[] floors;
    private static Score juego;
    protected static Star star;
    private double X;
    private double Y;

    protected Ladder[] ladders;
    protected Mario mario;
    protected Peach peach;
    protected DonkeyKong donkey;
    LinkedList<Barrel> barrels;
    boolean hasWon = false;
    boolean getBack = false;
    int velocity = 180;
    protected int dificulty = 1;
    protected int speedIncrease = 0;
    private int tiempo;
    private int timer;
    Random random= new Random();

    protected CollisionDetector collisions = new CollisionDetector();
    public Levels() {
        //Inicializa la lista de barriles
        barrels = new LinkedList<Barrel>();
        //Inicializamos la música
        StdAudio.loop("SFX/bacmusic.wav");
        //Suscribimos al timer
        Tiempo.getInstance().registrerObserver(this);
        //Creamos el layout del nivel actual
        SpawnLayout();
        juego=new Score();
        //tiempo:
        Tiempo.getInstance().registrerObserver(this); //Registro este objeto como observador.
        tiempo=0;
        timer=0;
    }

    //Se crean los pisos, escaleras, mario, peach, DK, etc. en sus posiciones iniciales
    abstract void SpawnLayout();
    /**
     * metodo usado por el observer.
     * @param segundos
     */
    public void upDate(int segundos){
        this.tiempo = segundos;
        if((segundos % dificulty == 0) && segundos != 0){
            velocity -= speedIncrease;
            if(velocity < 45){
                velocity = 45;
            }
        }
    }

    void RunGameplayLoop() {

        //Begin gameplay loop ********************************************
        while (mario.isAlive() && !hasWon && !mario.getPause() && !getBack ) {
            if (star.isActive()) {
                PennDraw.picture(0.5, 0.5, "fondoRage.jpg", 520, 1040);
            } else {
                PennDraw.clear(PennDraw.BLACK);
            }
            //draw 4 barrels in top corner
            //-------------------------------Cambiar esto
            Barrel.draw4(floors);

            //dibujamos todos los pisos y escaleras
            for (int i = 0; i < floors.length; i++) {
                floors[i].draw();
                if (i < ladders.length)
                    ladders[i].draw();
            }

            //draw donkey depending on what frame we are on using timer
            //Hacer una funcion aparte para poder controlar la velocidad
            if (0 <= timer && timer < (velocity - 35)) {
                donkey.drawOriginal();
            } else if ((velocity - 35) <= timer && timer < (velocity - 25)) {
                donkey.drawLeft();
            } else if ((velocity - 25) <= timer && timer < (velocity - 15)) {
                donkey.drawCenter();
            } else if ((velocity - 15) <= timer) {
                donkey.drawRight();
            } else donkey.drawOriginal();

            //dibujamos a peach
            peach.draw();

            //Control y animacion de Mario
            mario.Run();

            juego.Run(mario.getScore2());
            PennDraw.text(0.5, 0.95, (String.valueOf(mario.getScore2())));

            //dibujamos el tiempo.
            Tiempo.getInstance().draw();

            //if timer gets to 180 (frames), add a new barrel
            //----------Hacer una funcion aparte para cambiar velocidad

            if (timer % velocity == 0) {
                barrels.add(new Barrel(0.2, floors[0].getY()
                        + Floor.getHeight() + 0.025));
                collisions.setBarrels(barrels);
            }

            for (int i = 0; i < barrels.size(); i++) {
                if (!barrels.get(i).GetAlive()) {
                    barrels.remove(i);
                    collisions.setBarrels(barrels);
                    i--;
                }
            }


            //Revisa las colisiones de los barriles y los actualiza
            int counter1 = 0;
            while (counter1 < barrels.size()) {
                barrels.get(counter1).Run();
                counter1++;
            }

            timer++;
            if (timer >= velocity) {
                timer = 0;
            }

            star.Run(mario);
//---------------------------------------------------------TIEMPO-------------------------------------------------------
            /*if (mario.getTimerOn()){

                tiempoInicio = tiempo ; //tiempo...
                segundosTranscurridos = 0;
                mario.setTimerOn(false);
            }
            if(mario.getPowerUp()){
                if(segundosTranscurridos < duracionTotal) {
                    segundosTranscurridos = (int) (tiempo - tiempoInicio);
                    System.out.println("Segundos transcurridos: " + segundosTranscurridos);
                }else{
                    mario.setPowerUp(false);
                    generarStar=true;
                    starInMap=false;
                    segundosTranscurridos = 0;
                    star.setNoPowerUp();
                }
            }*/

            PennDraw.advance();
            hasWon = CollisionDetector.checkMarioCollision(peach.getX(), peach.getY(), 0.01, 0.015);
        }//end gameplay loop **********************************************

        PennDraw.disableAnimation();

        if (mario.getPause()) {
            Tiempo.getInstance().pausar();
            while (true) {

                PennDraw.picture(0.5, 0.6, "pause.png", 300, 150);
                PennDraw.setFontSize(20);
                PennDraw.setPenColor(Color.ORANGE);
                PennDraw.text(0.5, 0.15, "Press 'y' to go back");
                if (PennDraw.hasNextKeyTyped()) {
                    char dir = PennDraw.nextKeyTyped();
                    if (dir == 'p') {
                        Tiempo.getInstance().pausar();
                        mario.setPause(false);
                        PennDraw.enableAnimation(30);
                        break;
                    }
                    if (dir == 'y') {
                        Tiempo.getInstance().pausar();
                        PennDraw.clear(Color.black);
                        mario.resetScore();
                        PennDraw.picture(0.5,0.5,"replay.png",200,100);
                        mario.setPause(false);
                        getBack=true;
                        break;
                    }
                }
            }RunGameplayLoop();

        } if (hasWon) {
            PennDraw.clear(PennDraw.BLACK);
            mario.addScore(1000);
            PennDraw.text(0.5, 0.95, (String.valueOf(mario.getScore2())));
            StdAudio.play("SFX/win1.wav");
            timer=0;
            hasWon=false;
            PennDraw.picture(0.5, 0.6, "youwin.png", 250, 200);
            juego.registerScore("Player1", mario.getScore2(), tiempo);
        }if (!mario.isAlive()) {
            PennDraw.clear(PennDraw.BLACK);
            StdAudio.play("SFX/death.wav");
            timer=0;
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.setFontSize(80);
            PennDraw.picture(0.5, 0.6, "gameo.png", 300, 200);
            mario.resetScore();
            mario.setPause(false);
        }
    }

    public void setXY(){
        Random random = new Random();
        X=random.nextDouble()*0.6+0.2;
        Y=random.nextDouble()*0.8+0.1;
    }
}