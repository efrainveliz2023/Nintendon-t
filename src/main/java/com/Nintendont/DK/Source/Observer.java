package com.Nintendont.DK.Source;

public interface Observer {
    public int tiempo = 0;
    public void upDate(int segundos);
}


/*
* nota de efrain:
* para que los observadores se susbscriban:
*   Tiempo.getInstance().registrerObserver(this);
*
* tener implementado el:
* public void upDate(int segundos){
*   this.tiempo = segundos;  //siendo tiempo variable privada del objeto
* }
* */