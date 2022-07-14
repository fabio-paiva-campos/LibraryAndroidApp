package com.example.libraryapp.API;

import com.example.libraryapp.Recycler.RecyclerData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {
    @GET("Z0KS")
    Call<ArrayList<RecyclerData>> getAllBooks();
}