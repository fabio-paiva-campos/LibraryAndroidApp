package com.example.libraryapp.ui.bookmarks;

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
import com.example.libraryapp.Recycler.RecyclerViewAdapterDB;
import com.example.libraryapp.databinding.FragmentBookmarksBinding;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment implements RecyclerViewAdapterDB.OnItemClickListener {

    private FragmentBookmarksBinding binding;
    private RecyclerView Livro;
    private List<Book> recyclerDataArrayList;
    private RecyclerViewAdapterDB recyclerViewAdapter;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Livro = binding.idLivro;
        progressBar = binding.idPBLoading;

        recyclerDataArrayList = new ArrayList<>();
        getAllBooks();
        return root;
    }

    private void getAllBooks() {
        BookDAO bookDAO = new BookDAO(getContext());
        recyclerDataArrayList = bookDAO.listAll();

        for (int i = 0; i < recyclerDataArrayList.size(); i++) {
            recyclerViewAdapter = new RecyclerViewAdapterDB(recyclerDataArrayList, getContext());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            Livro.setLayoutManager(manager);
            Livro.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.addItemClickListener(BookmarksFragment.this);
        }
    }

    @Override
    public void onItemClick(int id, String titulo, String autor, String ano, String capa) {
        BookDAO bookDAO = new BookDAO(getContext());

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Excluir Livro");
        alert.setMessage("Excluir livro da sua lista?");

        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Book book = new Book(id, titulo, autor, ano, capa);
                bookDAO.delete(book);

                Toast.makeText(getContext(), "Livro excluído", Toast.LENGTH_SHORT).show();
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