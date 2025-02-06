package org.biblioteca.Dao;

import org.biblioteca.Model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements ILibro {

    private Connection conexion;

    public LibroDAO(Connection conexion){
        this.conexion = conexion;
    }

    @Override
    public void addBook(Libro libro){
        String sql = "Insert INTO Libros (titulo, autor, disponible) VALUES (?,?,?)";
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setBoolean(3,libro.isDisponible());
            ps.executeUpdate();
            System.out.println("Se inserto correctamente el Libro");
            System.out.println(libro);
        }catch (SQLException e) {
            System.err.println("Error al insertar el libro: " + e.getMessage());
        }


    }
    @Override
    public void deleteBook(int id) {
        String selectSql = "SELECT titulo FROM Libros WHERE id = ?";
        String deleteSql = "DELETE FROM Libros WHERE id = ?";

        try (PreparedStatement selectPs = conexion.prepareStatement(selectSql)) {
            // Primero, obtenemos el título del libro
            selectPs.setInt(1, id);
            ResultSet rs = selectPs.executeQuery();

            if (rs.next()) {
                String titulo = rs.getString("titulo");

                // Ahora, realizamos la eliminación
                try (PreparedStatement deletePs = conexion.prepareStatement(deleteSql)) {
                    deletePs.setInt(1, id);
                    int rowsAffected = deletePs.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("El libro '" + titulo + "' con ID " + id + " fue eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el libro con ID " + id + ".");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el libro: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontró el libro con ID " + id + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el libro: " + e.getMessage());
        }
    }
    @Override
    public boolean validacionAddBook(Libro libro) {
        String sql = "SELECT COUNT(*) FROM Libros WHERE id = ? AND titulo = ? AND autor = ?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, libro.getId());       // Validar por ID
            ps.setString(2, libro.getTitulo()); // Validar por nombre
            ps.setString(3, libro.getAutor()); // Validar por autor

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Si el COUNT(*) > 0, el libro existe
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si el libro existe: " + e.getMessage());
        }
        return false; // Si ocurre algún error o no se encuentra, devolver false
    }
    @Override
    public boolean validacionUpdateBook(int id){
        String sql = "SELECT COUNT(*) FROM Libros WHERE id = ?;";
        try(PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e){
            System.out.println("Error al verificar si el libro existe" + e.getMessage());
        }
        return false;
    }
    @Override
    public List<Libro> allBooks() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libros;";  // Seleccionamos todos los libros
        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Creamos el libro usando los valores obtenidos del ResultSet
                Libro libro = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),     // Título del libro
                        rs.getString("autor"),      // Autor del libro                         // ID recuperado directamente de la base de datos
                        rs.getBoolean("disponible") // Estado de disponibilidad
                );
                libros.add(libro);  // Añadimos el libro a la lista
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los libros: " + e.getMessage());
        }
        return libros;
    }
    @Override
    public boolean libroDisponible(int id) {
        String sql = "SELECT * FROM Libros WHERE id = ? AND disponible = 1";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                // Si hay algún resultado, significa que el libro está disponible
                if (rs.next()) {
                    return true;  // El libro está disponible
                } else {
                    return false; // El libro no está disponible o no existe
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la disponibilidad del libro: " + e.getMessage());
            return false;  // En caso de error, se devuelve false
        }
    }
    @Override
    public void actualizarDisponibilidad(int id, int disponible){
        String sql = "UPDATE Libros SET disponible = ? WHERE id = ?";
        try(PreparedStatement ps = conexion.prepareStatement(sql)){

            ps.setInt(1, disponible);
            ps.setInt(2, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("El libro con ID: " + id + " ha sido actualizado.");
            } else {
                System.out.println("No se encontró el libro con ID: " + id + ".");
            }
        }catch (SQLException e) {
            System.out.println("Error al actualizar la disponibilidad del libro: " + e.getMessage());
        }
    }
    @Override
    public void updateBook(int id, String titulo, String autor, boolean disponible) {
        String sql = "UPDATE Libros SET titulo = ?, autor = ?, disponible = ? WHERE id = ?;";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            // Configurar los valores del PreparedStatement
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setBoolean(3, disponible);
            ps.setInt(4, id);

            // Ejecutar la actualización
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Libro actualizado correctamente!");
            } else {
                System.out.println("No se encontró un libro con el ID proporcionado: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar el libro: " + e.getMessage());

        }
    }
    @Override
    public void verLibro(int id) {
        String sql = "SELECT * FROM Libros WHERE id = ?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                // Si hay algún resultado, significa que el libro existe
                if (rs.next()) {
                    int libroId = rs.getInt("id");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    boolean disponible = rs.getBoolean("disponible");
                    Libro libro = new Libro(libroId, titulo, autor, disponible);

                    System.out.println(libro);
                } else {
                    System.out.println("El libro no existe");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el libro: " + e.getMessage());
        }
    }

}
