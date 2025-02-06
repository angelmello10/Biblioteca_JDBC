package org.biblioteca.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // URL, usuario y contraseña de la base de datos en InfinityFree
    private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
