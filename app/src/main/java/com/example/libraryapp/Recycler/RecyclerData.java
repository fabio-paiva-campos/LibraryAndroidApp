package com.example.libraryapp.Recycler;

public class RecyclerData {
    private String titulo;
    private String capa;
    private String autor;
    private String ano;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public RecyclerData(String titulo, String capa, String autor, String ano) {
        this.titulo = titulo;
        this.capa = capa;
        this.autor = autor;
        this.ano = ano;
    }
}