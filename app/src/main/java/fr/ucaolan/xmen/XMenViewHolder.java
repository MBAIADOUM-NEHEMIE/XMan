package fr.ucaolan.xmen;

import android.view.ContextMenu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.ucaolan.xmen.databinding.XMenBinding;


public class XMenViewHolder extends RecyclerView.ViewHolder {
    private final XMenBinding ui ;

    private XMenAdapter.OnItemClickListener onItemClickListener;

    public XMenViewHolder (@NonNull XMenBinding ui ){
        super(ui.getRoot());
        this.ui = ui ;

    }

    public void setXMen(XMen xmen){
        ui.image.setImageResource(xmen.getIdImage());
        ui.nom.setText(xmen.getNom());
        ui.alias.setText(xmen.getAlias());
        ui.description.setText(xmen.getDescription());
        ui.pouvoirs.setText(xmen.getPouvoirs());

        itemView.setOnClickListener(this::onClick);

    }

    public void setOnItemClickListener(XMenAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void onClick(View v){
         if (onItemClickListener != null)
             onItemClickListener.onItemClick(getAdapterPosition());
    }

    public static final int MENU_EDIT = 1;
    public static final int MENU_DELETE = 2;

    public void onCreateConcertContexMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // position du X-Men concerné
        int position = getAdapterPosition();
        // titre du menu = nom du X-Men
        menu.add(0, MENU_EDIT, position, "Edit");
        menu.add(0, MENU_DELETE, position, "Delete");
    }


}
