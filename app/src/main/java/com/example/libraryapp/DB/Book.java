package com.example.libraryapp.DB;

import java.io.Serializable;

public class Book implements Serializable {
    private Integer id;
    private String titulo;
    private String autor;
    private String ano;
    private String capa;

    public Book(Integer id, String titulo, String autor, String ano, String capa){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.capa = capa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    @Override
    public String toString(){
        return String.format("%s\n%s", titulo, autor, ano, capa);
    }
}
