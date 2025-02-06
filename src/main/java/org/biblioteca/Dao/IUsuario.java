package org.biblioteca.Dao;

import org.biblioteca.Model.Usuario;

import java.util.List;

public interface IUsuario {
    void addUsuario(Usuario usuario);
    void deleteUser(int id);
    boolean validacionAddUser(Usuario usuario);
    boolean updateUser(int id);
    List<Usuario> allUsers();
    void updateUsers(int id, String nombre, String apellido);
    void searchUser(String nombre, String apellido);
    void librosPrestadosUser(List<Usuario> usuarios, int id);
}
