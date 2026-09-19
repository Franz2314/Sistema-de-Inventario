package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/inventario";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    /** Abre una conexión nueva; si falla lanza SQLException para que quien llama informe al usuario. */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
