package fr.ucaolan.xmen;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.ucaolan.xmen.databinding.XMenBinding;

public class XMenAdapter extends RecyclerView.Adapter<XMenViewHolder> {
    private  List<XMen> liste;

    private XMenAdapter.OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public XMenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        XMenBinding ui = XMenBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent ,
                false);
        return new XMenViewHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull XMenViewHolder holder, int position) {

        holder.setXMen(liste.get(position));

        holder.setOnItemClickListener(onItemClickListener);

    }

    @Override
    public int getItemCount() {
        return liste.toArray().length;
    }



    public XMenAdapter(List<XMen> liste) {
        this.liste = liste;
    }

    public  interface OnItemClickListener {

        void  onItemClick(int position);

    }

    public void setOnItemClickListener(XMenAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



}
