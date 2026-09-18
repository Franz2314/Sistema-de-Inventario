/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/inventario";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
            System.out.println("✔ Conexión exitosa a la BD");
            return con;
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión:");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
