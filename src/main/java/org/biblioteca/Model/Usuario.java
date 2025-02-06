package org.biblioteca.Model;

import java.util.List;

public class Usuario {


    private int id;
    private String nombre;
    private String apellido;
    private List<Libro> librosPrestados;


    public Usuario(int id, String nombre, String apellido, List<Libro> librosPrestados) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.librosPrestados = librosPrestados;
    }
    public Usuario(String nombre, String apellido) {

        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }




    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID del Usuario: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append(" ").append(apellido).append("\n");
        sb.append("Lista de libros prestados. \n");

        if(librosPrestados.isEmpty()){
            sb.append("No tienes libros prestados! \n");
        } else {
            for (Libro libro : librosPrestados) {
                sb.append("- ").append(libro.getTitulo()).append(" por ").append(libro.getAutor()).append("\n");

            }
        }
        return sb.toString();
    }
}


