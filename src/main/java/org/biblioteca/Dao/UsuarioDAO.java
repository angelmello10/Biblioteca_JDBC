package org.biblioteca.Dao;

import org.biblioteca.Model.Usuario;
import org.biblioteca.Util.Conexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAO implements IUsuario{





    @Override
    public void addUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombre, apellido) VALUES (?,?)";
        try(Connection conexion = Conexion.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.executeUpdate();
            System.out.println("Se añadió correctamente al nuevo usuario:  ");
            System.out.println(usuario);
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public boolean validacionAddUser(Usuario usuario) {
       String sql = "SELECT COUNT(*) FROM Usuarios WHERE id = ? AND nombre = ?;";
       try(Connection conexion = Conexion.getConnection();
            PreparedStatement ps = conexion.prepareStatement(sql)){
           ps.setInt(1, usuario.getId());
           ps.setString(2, usuario.getNombre());

           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               int count = rs.getInt(1);
               return count > 0;
           }
       } catch (SQLException e) {
           System.err.println("Error al verificar si el usuario existe: " + e.getMessage());

       }
       return false;
    }

    @Override
    public boolean updateUser(int id) {
        return false;
    }

    @Override
    public List<Usuario> allUsers() {
        return List.of();
    }

    @Override
    public void updateUsers(int id, String nombre, String apellido) {

    }

    @Override
    public void searchUser(String nombre, String apellido) {

    }

    @Override
    public void librosPrestadosUser(List<Usuario> usuarios, int id) {

    }
}
