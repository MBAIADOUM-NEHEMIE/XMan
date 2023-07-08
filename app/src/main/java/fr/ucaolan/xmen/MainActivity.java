package fr.ucaolan.xmen;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.ucaolan.xmen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    // interface de l'utilisateur
    private ActivityMainBinding ui;
    private List<XMen> liste;
    private XMenAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mise en place de l'interface
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        // obtenir la liste
        XMenApplication application = (XMenApplication) getApplication();
        List<XMen> liste = application.getListe();

        // créer l'adaptateur
        XMenAdapter adapter = new XMenAdapter(liste);

        // fournir l'adaptateur au recycler
        ui.recycler.setAdapter(adapter);

        // dimensions constantes
        ui.recycler.setHasFixedSize(true);

        // layout manager
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        ui.recycler.setLayoutManager(lm);

        // ligne de separation entre items
        // seprateurs
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL);
        ui.recycler.addItemDecoration(dividerItemDecoration);

        // Creation de l'ecouteur sur le click

        adapter.setOnItemClickListener(this::OnItemClick);

        //adapter.setOnItemClickListener(this);

        // initialisation de la variable EditLauncher

        EditActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::onEditActivityFinished);
    }

    private void onEditActivityFinished(ActivityResult result) {

        if (result.getResultCode() == RESULT_OK){
            // Appel de l'activité EditActivity à faire


        }
        Intent intent = new Intent(this, EditActivity.class);
        EditActivityLauncher.launch(intent);
    }

    private void OnItemClick(int position){

        // Récupérer les X-Men concerné

        XMen xmen = liste.get(position);
        // Changer l'image du X-Men

        xmen.setIdImage(R.drawable.undef);

        // Signaler à l'adaptateur que l'element a changer
        adapter.notifyItemChanged(position);
    }

    public boolean OnCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        XMenApplication application = (XMenApplication) getApplication();

        // Selon l'item séléctionné

        int iditem = item.getItemId();

        if (iditem == R.id.reinit){

            // vider la liste

            application.initListe();

            //  signaler le changement à l'adaptateur

            adapter.notifyItemChanged(iditem);


            return true;
        }
        if (iditem == R.id.create){

            // TODO voir l'exercice suivant

            return true;
        }

        return  super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    // Gestion du passage de MainActivity à EditActivity

    private ActivityResultLauncher<Intent> EditActivityLauncher;

    private void onEdit(int position){

        //String EXTRA_POSITION = "position";

        Intent intent = new Intent(this, EditActivity.class);

        intent.putExtra("position", position);

        // Demarrer l'activité avec attente du résultat

        EditActivityLauncher.launch(intent);

        //intent.putExtra("EXTRA_POSITION", position);

        Intent intentextra = getIntent();

        //EXTRA_POSITION= intentextra.getStringExtra("EXTRA_POSITION");


    }

    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        // recupérer la position de l'élément concerné par le menu (dans order)
        int position = item.getOrder();

        // selon le bouton de menu
        switch (item.getItemId()) {
            case XMenViewHolder.MENU_EDIT:
                onEdit(position);
                return true;
            case XMenViewHolder.MENU_DELETE:
                // TODO décommenter quand cette méthode sera ajoutée, voir la fin du sujet
                //onDelete(position);
                return true ;

        }
        return super.onContextItemSelected(item);


    }

    private void onReallyDelete(int position)
    {
        liste.remove(position);
//  signaler à l'adaptateur que cette position a été supprimée

        adapter.notifyItemChanged(position);

    }

    private void onDelete(int position)
    {
        XMen xmen = liste.get(position);
        new AlertDialog.Builder(this)
                .setTitle(xmen.getNom())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Vous confirmez la suppression ?")
// le bouton "oui" supprime vraiment
                .setPositiveButton(android.R.string.ok,
                        (dialog, idbtn) -> onReallyDelete(position))
// le bouton "non" ne fait rien
                .setNegativeButton(android.R.string.cancel, null)
// affichage du dialogue
                .show();
    }

    }