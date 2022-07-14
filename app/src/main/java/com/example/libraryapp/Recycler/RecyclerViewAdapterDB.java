package com.example.libraryapp.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.DB.Book;
import com.example.libraryapp.databinding.CardLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapterDB extends RecyclerView.Adapter<RecyclerViewAdapterDB.RecyclerViewHolder> {

    private final Context context;
    private OnItemClickListener itemClickListener;
    private List<Book> bookDataArrayList;
    private CardLayoutBinding binding;

    public RecyclerViewAdapterDB(List<Book> recyclerDataArrayList, Context context) {
        this.bookDataArrayList = recyclerDataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardLayoutBinding itemBinding = CardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerViewHolder(itemBinding);
    }

    public void addItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        Book modal = bookDataArrayList.get(position);
        holder.titulo.setText(modal.getTitulo());
        holder.ano.setText(modal.getAno());
        holder.autor.setText(modal.getAutor());
        Picasso.get().load(modal.getCapa()).into(holder.capa);

        holder.capa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(
                        holder.getAdapterPosition(), modal.getTitulo(), modal.getAutor(), modal.getAno(), modal.getCapa());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, autor, ano;
        private ImageView capa;

        public RecyclerViewHolder(@NonNull CardLayoutBinding itemBinding) {
            super(itemBinding.getRoot());
            titulo = itemBinding.idTitulo;
            autor = itemBinding.idAutor;
            ano = itemBinding.idAno;
            capa = itemBinding.idCapa;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String titulo, String autor, String ano, String capa);
    }
}