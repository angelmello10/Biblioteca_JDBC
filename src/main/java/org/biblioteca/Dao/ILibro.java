package org.biblioteca.Dao;

import org.biblioteca.Model.Libro;

import java.util.List;

public interface ILibro {
    void addBook(Libro libro);
    void deleteBook(int id);
    boolean validacionAddBook(Libro libro);
    boolean validacionUpdateBook(int id);
    List<Libro> allBooks();
    boolean libroDisponible(int id);
    void actualizarDisponibilidad(int id, int disponible);
    void updateBook(int id, String titulo, String autor,boolean disponible);
    void verLibro(int id);
}
