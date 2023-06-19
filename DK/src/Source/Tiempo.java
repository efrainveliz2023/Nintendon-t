package Source;

import Resources.PennDraw;

import java.util.ArrayList;

/**
 * con patron de diseño creacional Singleton.
 */
public class Tiempo implements Runnable,Subject{
    private ArrayList<Observer> observers;
    private static Tiempo instance;
    private int minutos;
    private int segundos;
    private boolean pausa;
    private Tiempo(){
        minutos = 0;
        segundos = 0;
        pausa = false;
        observers = new ArrayList<>();
    }
    public static Tiempo getInstance() {
        if (instance == null) {
            instance = new Tiempo();
        }
        return instance;
    }
    @Override
    public void run() {
        while(true){
            setSegundos(getSegundos()+1);
            if(getSegundos()==60){
                setSegundos(0);
                setMinutos(getMinutos()+1);
            }
            notifyObservers(getSegundosTotales());
            //System.out.println("tiempo total: "+getSegundosTotales());
            try { Thread.sleep(1000); }catch (InterruptedException e){e.printStackTrace();}
            while(pausa){ try { Thread.sleep(1); }catch (InterruptedException e){e.printStackTrace();} }
        }
    }
    private synchronized void setSegundos(int segundos) { this.segundos = segundos; }
    private synchronized void setMinutos(int minutos) { this.minutos = minutos; }
    public int getSegundos() { return segundos; }
    private int getMinutos() { return minutos; }

    /**
     * usado por el sujeto para leer la cantidad de segundos
     * y luego mostrarselos a los observadores.
     */
    public int getSegundosTotales(){
        return (getSegundos()+(getMinutos()*60));
    }
    public void reset(){ setSegundos(0); setMinutos(0); }
    public void pausar(){  //cambia el estado de pausar.
        if(pausa == false){
            pausa = true;
        }else{
            pausa = false;
        }
    }
    public void draw(){
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.setFontSize(15);
        PennDraw.text(0.1, 0.95, String.format("Time %d:%d",minutos,segundos));
    }
    //implementacion de metodos de la interface Subject
    @Override
    public void registrerObserver(Observer o) {
        observers.add(o);
    }


    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(int seg) {
        for(int i = 0; i < observers.size(); i++){
            observers.get(i).upDate(seg);
        }
    }
}
