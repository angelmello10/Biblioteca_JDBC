package org.biblioteca.Model;

public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private boolean disponible;



    public Libro (int id, String titulo, String autor, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }
    public  Libro (String titulo, String autor, boolean disponible){
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setAutor (String autor){
        this.autor = autor;
    }
    public String getAutor(){
        return autor;
    }
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
    public boolean isDisponible(){
        return disponible;
    }
    public int getId(){
        return id;
    }
    public void set(int id){
        this.id = id;
    }


    @Override
    public String toString(){
        return "Libro con Identificador: " + id + "\n"+
                "Titulo: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Disponible: " + (disponible ? "Sí" : "No");
    }
}
