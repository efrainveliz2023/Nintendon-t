package Source;
import Resources.PennDraw;
import Resources.StdAudio;

public abstract class Levels implements Observer {
    protected Floor[] floors;
    protected Ladder[] ladders;
    protected Mario mario;
    protected Peach peach;
    protected DonkeyKong donkey;
    LinkedList<Barrel> barrels;
    boolean hasWon = false;
    int velocity = 180;
    protected int dificulty = 1;
    protected int speedIncrease = 0;

    public Levels() {
        //Inicializa la lista de barriles
         barrels = new LinkedList<Barrel>();
        //Inicializamos la música
        StdAudio.loop("SFX/bacmusic.wav");
        //Suscribimos al timer
        Tiempo.getInstance().registrerObserver(this);
        //Creamos el layout del nivel actual
        SpawnLayout();
    }

    //Se crean los pisos, escaleras, mario, peach, DK, etc. en sus posiciones iniciales
    abstract void SpawnLayout();

    public void upDate(int segundos){
        if((segundos % dificulty == 0) && segundos != 0){
            velocity -= speedIncrease;
            if(velocity < 45){
                velocity = 45;
            }
        }
    }

    void RunGameplayLoop(){
        int timer = 0;

        //Begin gameplay loop ********************************************
        while(mario.isAlive() && !hasWon) {
            PennDraw.clear(PennDraw.BLACK);

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
            }
            else if ((velocity - 35) <= timer && timer < (velocity - 25)) {
                donkey.drawLeft();
            }
            else if ((velocity - 25) <= timer && timer < (velocity - 15)) {
                donkey.drawCenter();
            }
            else if ((velocity - 15) <= timer) {
                donkey.drawRight();
            }
            else donkey.drawOriginal();

            //dibujamos a peach
            peach.draw();

            //Control y animacion de Mario
            mario.Run();

            //dibujamos el tiempo.
            Tiempo.getInstance().draw();
            
            //if timer gets to 180 (frames), add a new barrel
            //----------Hacer una funcion aparte para cambiar velocidad
            if (timer % velocity == 0) {
                barrels.add(new Barrel(0.2, floors[0].getY()
                        + Floor.getHeight() + 0.025));
            } else if (barrels.size() > 15) {
                //No se si esto es necesario,
                barrels.remove(0);
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

            PennDraw.advance();
            hasWon = CollisionDetector.checkMarioCollision(peach.getX(), peach.getY(), 0.01, 0.015);
        }//end gameplay loop **********************************************

        PennDraw.disableAnimation();

        if (hasWon) {
            StdAudio.play("SFX/win1.wav");
            PennDraw.setPenColor(PennDraw.GREEN);
            PennDraw.setFontSize(100);
            PennDraw.text(0.5, 0.5, "YOU WON!");
        }
        else if (!mario.isAlive()) {
            StdAudio.play("SFX/death.wav");
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.setFontSize(100);
            PennDraw.text(0.5, 0.5, "YOU LOST!");
        }
    }
}
