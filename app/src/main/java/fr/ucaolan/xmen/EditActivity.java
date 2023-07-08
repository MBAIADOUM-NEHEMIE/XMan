package fr.ucaolan.xmen;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import fr.ucaolan.xmen.databinding.ActivityEditBinding;


public class EditActivity extends AppCompatActivity {

    private List<XMen> liste;

    private ActivityEditBinding ui;

    String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // initialisation de l'interface

        ui = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        // obtenir la liste
        XMenApplication application = (XMenApplication) getApplication();
        List<XMen> liste = application.getListe();

    }

    public void onAccept(MenuItem item){

        // Créer un XMen avec les informations saisies par l'utilisateur

        XMen xmen = new XMen();

        xmen.setNom(ui.nom.getText().toString());
        xmen.setAlias(ui.alias.getText().toString());
        xmen.setPouvoirs(ui.pouvoirs.getText().toString());
        xmen.setDescription(ui.description.getText().toString());
        xmen.setIdImage(R.drawable.undef);


        liste.add(xmen);

        // Terminer l'activité en indiquant un succès

        setResult(RESULT_OK);
        finish();
    }

    /**
     * @param xmen
     */
    private void setXMen(XMen xmen){

        // mettre les informations du XMen dans les vues

        ui.nom.setText(xmen.getNom());

        ui.alias.setText(xmen.getNom());

        ui.description.setText(xmen.getNom());

        ui.pouvoirs.setText(xmen.getNom());

    }




}



