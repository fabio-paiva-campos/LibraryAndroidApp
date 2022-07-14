package com.example.libraryapp.ui.books;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.DB.Book;
import com.example.libraryapp.DB.BookDAO;
import com.example.libraryapp.Recycler.RecyclerData;
import com.example.libraryapp.Recycler.RecyclerViewAdapter;
import com.example.libraryapp.API.RetrofitAPI;
import com.example.libraryapp.databinding.FragmentBooksBinding;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksFragment extends Fragment implements RecyclerViewAdapter.OnItemClickListener {

    private FragmentBooksBinding binding;
    private RecyclerView Livro;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Livro = binding.idLivro;
        progressBar = binding.idPBLoading;

        recyclerDataArrayList = new ArrayList<>();
        getAllBooks();
        return root;
    }

    private void getAllBooks() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonkeeper.com/b/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofit2.Call<ArrayList<RecyclerData>> call = retrofitAPI.getAllBooks();
        call.enqueue(new Callback<ArrayList<RecyclerData>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<RecyclerData>> call, Response<ArrayList<RecyclerData>> response) {
                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    recyclerDataArrayList = response.body();
                    for (int i = 0; i < recyclerDataArrayList.size(); i++) {
                        recyclerViewAdapter = new RecyclerViewAdapter(recyclerDataArrayList, getContext());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        Livro.setLayoutManager(manager);
                        Livro.setAdapter(recyclerViewAdapter);
                        recyclerViewAdapter.addItemClickListener(BooksFragment.this);
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<RecyclerData>> call, Throwable t) {
                Toast.makeText(getContext(), "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position, String titulo, String autor, String ano, String capa) {
        BookDAO bookDAO = new BookDAO(getContext());

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Salvar Livro");
        alert.setMessage("Salvar livro para sua lista?");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Book book = new Book(0, titulo, autor, ano, capa);
                bookDAO.save(book);

                Toast.makeText(getContext(), "Livro salvo", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        alert.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}