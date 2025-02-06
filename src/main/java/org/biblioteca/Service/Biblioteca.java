package org.biblioteca.Service;
import org.biblioteca.Dao.UsuarioDAO;
import org.biblioteca.Model.Libro;
import org.biblioteca.Model.Usuario;
import org.biblioteca.Util.Conexion;
import org.biblioteca.Dao.LibroDAO;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;

public class Biblioteca {


    private Scanner sca;
    private LibroDAO libroDao;
    private UsuarioDAO usuarioDAO;

    // Constructor de la clase Biblioteca
    public Biblioteca() {
        sca = new Scanner(System.in);
        // Inicializar la conexión a la base de datos
        Connection connection = Conexion.getConnection();
        this.libroDao = new LibroDAO(connection);

    }


    public void listarLibros() {
        // Obtener la lista de libros desde la base de datos
        List<Libro> libros = libroDao.allBooks();

        // Verificar si hay libros disponibles
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles.");
            return;
        }

        // Mostrar los libros disponibles
        System.out.println("Estos son los libros disponibles: ");
        for (Libro allBook : libros) {
            System.out.println(allBook);  // Esto invoca el método toString() de la clase Libro
        }
    }

    public void addLibro() {
        System.out.println("Para registrar un nuevo libro ingresa la siguiente información.");
        System.out.print("Ingresa el Título del libro: ");
        String titulo = sca.nextLine();
        System.out.print("Ingresa el Autor del libro: ");
        String autor = sca.nextLine();
        boolean disponible = true;

        // Crear el libro sin el ID, ya que será generado por la base de datos
        Libro bookNew = new Libro(titulo, autor, disponible);

        // Verificar si el libro ya existe en la base de datos
        if (libroDao.validacionAddBook(bookNew)) {
            System.out.println("El libro '" + bookNew.getTitulo() + "' de " + bookNew.getAutor() + " ya existe!");
            return;
        }

        // Insertar el libro en la base de datos
        libroDao.addBook(bookNew);
    }

    public void eliminarLibro(){
        System.out.print("Ingresa el ID del libro que deseas eliminar: ");
        int id = sca.nextInt();  //
        libroDao.deleteBook(id);
    }

    public void verLibro(){
        System.out.print("Ingresa el ID del libro que deseas validar: ");
       int idLibro = sca.nextInt();
        sca.nextLine();
        libroDao.verLibro(idLibro);
    }

    public void disponibilidadLibro() throws SQLException {
        System.out.print("Ingresa el ID del libro para consultar si está disponible: ");

        // Verificar si el valor ingresado es un entero válido
        if (!sca.hasNextInt()) {
            System.out.println("Por favor ingresa un número válido para el ID del libro.");
            sca.nextLine();  // Limpiar el buffer
            return;  // Salir del método si no se ingresa un número válido
        }

        int idLibro = sca.nextInt();
        sca.nextLine();  // Limpiar el buffer después de leer el entero

        boolean disponible = libroDao.libroDisponible(idLibro);

        // Usar la condición directa sin necesidad de comparar con 'true'
        if (disponible) {
            System.out.println("El libro con ID: " + idLibro + " está disponible.");
        } else {
            System.out.println("El libro con ID: " + idLibro + " no se encuentra disponible.");
        }
    }

    public void cambiarDisponibilidad() {
        // Solicitar el ID del libro a modificar
        System.out.print("Ingresa el ID del libro a modificar: ");
        int id = sca.nextInt();
        sca.nextLine(); // Limpiar el buffer

        // Solicitar la nueva disponibilidad
        System.out.print("Presiona 1 para DISPONIBLE o 2 para NO DISPONIBLE: ");
        int disp = sca.nextInt();
        sca.nextLine(); // Limpiar el buffer

        // Convertir la opción seleccionada en un valor numérico para la base de datos
        int disponible;
        String estatus = "";

        if (disp == 1) {
            disponible = 1;  // Disponible (1)
            estatus = "Disponible";
        } else if (disp == 2) {
            disponible = 0; // No disponible (0)
            estatus = "No Disponible";
        } else {
            System.out.println("Opción no válida. Por favor ingresa 1 o 2.");
            return;  // Salir del método si la opción no es válida
        }

        // Llamar al método del DAO para actualizar la disponibilidad
        libroDao.actualizarDisponibilidad(id, disponible);
        System.out.println("La disponibilidad del libro con ID " + id + " ha sido actualizada a: " + estatus);
    }

    public void updateBook() {
        try {
            System.out.print("Ingresa el ID del libro que deseas actualizar: ");
            int idBook = sca.nextInt();
            sca.nextLine();

            if (libroDao.validacionUpdateBook(idBook)) {
                System.out.println("El libro a actualizar es: ");
                libroDao.verLibro(idBook);
                System.out.print("Ingresa el título a actualizar: ");
                String titulo = sca.nextLine();
                System.out.print("Ingresa el autor a actualizar: ");
                String autor = sca.nextLine();

                System.out.print("Presiona 1 para indicar si el libro está disponible o 2 si no lo está: ");
                int disp = sca.nextInt();
                sca.nextLine();

                boolean disponible;

                if (disp == 1) {
                    disponible = true;

                } else if (disp == 2) {
                    disponible = false;

                } else {
                    System.out.println("Opción no válida. Por favor ingresa 1 o 2.");
                    return;
                }

                libroDao.updateBook(idBook, titulo, autor, disponible);

                System.out.println("El libro ha sido actualizado correctamente: ");
                libroDao.verLibro(idBook);
            } else {
                System.out.println("El ID proporcionado no corresponde a ningún libro existente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no válida. Por favor ingresa un número entero.");
            sca.nextLine(); // Limpiar el buffer del scanner
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    public void addUser(){
        System.out.println("Para registrar un nuevo Usuario ingresa la siguiente información.");
        System.out.print("Ingresa el Nombre(s) del usuario: ");
        String nombre = sca.nextLine();
        System.out.print("Ingresa los Apellidos: ");
        String apellido = sca.nextLine();

        // Crear el usuario sin el ID, ya que será generado por la base de datos
        Usuario userNew = new Usuario(nombre, apellido);

        // Verificar si el libro ya existe en la base de datos
        if (usuarioDAO.validacionAddBook(bookNew)) {
            System.out.println("El libro '" + bookNew.getTitulo() + "' de " + bookNew.getAutor() + " ya existe!");
            return;
        }

        // Insertar el libro en la base de datos
        libroDao.addBook(bookNew);
    }
}

