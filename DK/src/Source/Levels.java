package Source;
import Resources.PennDraw;
import Resources.StdAudio;
import java.util.Random;

public abstract class Levels implements Observer {
    protected Floor[] floors;
    private static Score juego;

    private int duracionTotal = 10;
    private long tiempoInicio = System.currentTimeMillis();
    private int segundosTranscurridos;
    private boolean generarStar=true;
    private static Star star;
    private double X;
    private double Y;

    protected Ladder[] ladders;
    protected Mario mario;
    protected Peach peach;
    protected DonkeyKong donkey;
    LinkedList<Barrel> barrels;
    LinkedList<Fireball>  fireballs;
    boolean fireballPlay=false;
    Fireball frball;
    boolean hasWon = false;
    boolean fireball=false;
    int velocity = 180;
    private boolean starInMap=false;
    protected int dificulty = 1;
    protected int speedIncrease = 0;

    public Levels() {
        //Inicializa la lista de barriles
         barrels = new LinkedList<Barrel>();
        fireballs=new LinkedList<Fireball>();
        //Inicializamos la música
        StdAudio.loop("SFX/bacmusic.wav");
        //Suscribimos al timer
        Tiempo.getInstance().registrerObserver(this);
        //Creamos el layout del nivel actual
        star=new Star();
        SpawnLayout();
        juego=new Score();
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

            juego.Run(mario.getScore2());
            PennDraw.text(0.5, 0.9,(String.valueOf(mario.getScore2())));

            //dibujamos el tiempo.
            Tiempo.getInstance().draw();
            
            //if timer gets to 180 (frames), add a new barrel
            //----------Hacer una funcion aparte para cambiar velocidad
            if (timer % velocity == 0) {
                barrels.add(new Barrel(0.2, floors[0].getY()
                        + Floor.getHeight() + 0.025));
            }

            for(int i = 0; i < barrels.size(); i++){
                if(!barrels.get(i).GetAlive()){
                    barrels.remove(i);
                    i--;
                }
            }


            //Revisa las colisiones de los barriles y los actualiza
            int counter1 = 0;
            while (counter1 < barrels.size()) {
                barrels.get(counter1).Run(fireballs);
                counter1++;
            }

            if(mario.fireball==true){
                fireballPlay=true;
                frball = new Fireball(mario.getX(), mario.getY(), mario.getLastKeyPressed());
                fireballs.add(frball);
                mario.fireball=false;
            }

            if(fireballPlay) {

                for (int i=0; i < fireballs.size();i++){
                    fireballs.get(i).Run(mario);

                }
            }

            timer++;
            if (timer >= velocity) {
                timer = 0;
            }

            // Generar un número aleatorio


            if(generarStar){
                Random random= new Random();
                if (random.nextDouble() <= 0.1){
                    if(!starInMap){
                        setXY();
                        generarStar = false;
                    }
                }}

            if(!generarStar){
                star.Run(X,Y,mario);
            }
//---------------------------------------------------------TIEMPO--------------------------------
            if (mario.getTimerOn()){
                tiempoInicio = System.currentTimeMillis();
                segundosTranscurridos = 0;
                mario.setTimerOn(false);
                //update(seconds)
            }


            if(mario.getPowerUp()){
                while (segundosTranscurridos < duracionTotal) {
                    long tiempoActual = System.currentTimeMillis();
                    long tiempoTranscurrido = tiempoActual - tiempoInicio;
                    segundosTranscurridos = (int) (tiempoTranscurrido / 1000); // Convertir milisegundos a segundos

                    System.out.println("Segundos transcurridos: " + segundosTranscurridos);

                    if (tiempoTranscurrido < (segundosTranscurridos + 1) * 1000) {
                        break;
                    }
                }

            }

            if(segundosTranscurridos >= duracionTotal){
                mario.setPowerUp(false);
                generarStar=true;
                starInMap=false;
                segundosTranscurridos = 0;
                star.setNoPowerUp();
            }
//----------------------------------------------------------------------------------------------------
            if(fireballs.size()>10){
                fireballs.remove(0);
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
            mario.addScore(1000);
            juego.registerScore("Player1",mario.getScore2());
            mario.resetScore();
        }
        else if (!mario.isAlive()) {
            StdAudio.play("SFX/death.wav");
            PennDraw.setPenColor(PennDraw.RED);
            PennDraw.setFontSize(100);
            PennDraw.text(0.5, 0.5, "YOU LOST!");
            mario.resetScore();
        }
    }

    public void setXY(){
        Random random = new Random();
        X=random.nextDouble()*0.6+0.2;
        Y=random.nextDouble()*0.8+0.1;
    }
}
