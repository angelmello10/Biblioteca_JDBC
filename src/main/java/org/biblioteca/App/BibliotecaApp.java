package org.biblioteca.App;

import org.biblioteca.Service.Biblioteca;
import org.biblioteca.Util.Conexion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        System.out.println("¡Bienvenido a la biblioteca Intellij!");
        int opcion = 0;
        try {
            do {
                // Menú de opciones
                System.out.println("• Opción 1: Registrar un nuevo usuario.");
                System.out.println("• Opción 2: Mostrar usuarios registrados.");
                System.out.println("• Opción 3: Agregar un libro al inventario.");
                System.out.println("• Opción 4: Ver libros del inventario.");
                System.out.println("• Opción 5: Eliminar un Libro.");
                System.out.println("• Opción 6: Prestar un libro a un usuario.");
                System.out.println("• Opción 7: Devolver un libro.");
                System.out.println("• Opción 8: Consultar disponibilidad de libro.");
                System.out.println("• Opción 9: Cambiar disponibilidad del libro.");
                System.out.println("• Opción 10: Editar un Libro del inventario.");
                System.out.println("• Opción 11: Ver información de un libro.");
                System.out.println("• Opción 12: Salir.");
                System.out.print("Selecciona una opción en el menú: ");

                try {
                    opcion = sca.nextInt();
                    sca.nextLine();  // Consumir el salto de línea después de leer el entero
                } catch (InputMismatchException e) {
                    System.out.println("Error: Entrada no válida. Por favor ingresa un número entero.");
                    sca.nextLine(); // Limpiar el buffer de entrada
                    continue;  // Esto hace que vuelva a pedir la opción sin salir del ciclo
                }

                switch (opcion) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        biblioteca.addLibro();
                        break;
                    case 4:
                        biblioteca.listarLibros();
                        break;
                    case 5:
                        biblioteca.eliminarLibro();
                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    case 8:
                        biblioteca.disponibilidadLibro();
                        break;
                    case 9:
                        biblioteca.cambiarDisponibilidad();
                    case 10:
                        biblioteca.updateBook();
                    case 11:
                        biblioteca.verLibro();
                        case 12:
                        System.out.println("¡Gracias por usar la biblioteca virtual. ¡Hasta luego!");
                        Conexion.closeConnection();
                        break;

                    default:
                        System.out.println("Entrada invalida, selecciona la opción correcta!");
                        break;
                }
            } while (opcion != 12);  // El ciclo se ejecutará hasta que el usuario elija la opción 12
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }

    }
}

