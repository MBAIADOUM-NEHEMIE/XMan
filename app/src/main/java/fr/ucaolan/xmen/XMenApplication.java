package fr.ucaolan.xmen;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

import kotlin.text.UStringsKt;

public class XMenApplication extends Application {
    // variable globale de l'application : la liste des XMen
    private final List<XMen> liste = new ArrayList<>();
    // initialisation du contexte
    @Override public void onCreate(){
        super.onCreate();
        // TODO initialiser la liste à partir des ressources , voir les explications
        // accès aux  ressources
        Resources res= getResources();
        final String[] noms = res.getStringArray(R.array.noms);
        final String[] description = res.getStringArray(R.array.descriptions);
        final String[] alias = res.getStringArray(R.array.alias);
        final String[] pouvoirs = res.getStringArray(R.array.pouvoirs);


        TypedArray images = res.obtainTypedArray(R.array.idimages);


        // recopier les donnéex dans la liste
        for(int i = 0 ; i < noms.length ; i++){
            // TODO constrcuteuravec les pramètres
            XMen xm = new XMen(
                    noms[i],
                    alias[i],
                    description[i],
                    pouvoirs[i],
                    images.getResourceId(i , R.drawable.undef)
            );
            // TODO add Xmen on liste created

            liste.add(xm);
        }

        // liberer certaines ressources explicitement
        images.recycle();

    }

    public List<XMen> getListe() {
        return liste;

    }

    public void initListe(){

        // TODO initialiser la liste à partir des ressources , voir les explications
        // accès aux  ressources
        Resources res= getResources();
        final String[] noms = res.getStringArray(R.array.noms);
        final String[] description = res.getStringArray(R.array.descriptions);
        final String[] alias = res.getStringArray(R.array.alias);
        final String[] pouvoirs = res.getStringArray(R.array.pouvoirs);


        TypedArray images = res.obtainTypedArray(R.array.idimages);


    }


}
