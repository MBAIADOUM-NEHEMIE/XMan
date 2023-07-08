package fr.ucaolan.xmen;

import android.app.Application;

public class TP4Application extends  Application {
private  int compteur;
    @Override
    public  void onCreate() {
        super.onCreate();
       this.compteur = 0;
        TP4Application app =  (TP4Application) getApplicationContext();
        int compteur = app.getCompteur();



    }
    public int getCompteur(){
        return compteur;
    }
}