/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class ProductoDAO {

    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT id, nombre, precio, stock FROM productos";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return lista;
    }


    public void insertar(Producto p) {

        String sql =
            "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());

            ps.executeUpdate();

            System.out.println("Producto insertado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
        }
    }


    public void actualizar(Producto p) {

        String sql =
            "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId());

            ps.executeUpdate();

            System.out.println("Producto actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
        }
    }


    public void eliminar(String codigo) {

        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(codigo));

            ps.executeUpdate();

            System.out.println("Producto eliminado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }


    public List<Producto> buscarPorNombre(String texto) {

        List<Producto> lista = new ArrayList<>();

        String sql =
            "SELECT id, nombre, precio, stock " +
            "FROM productos WHERE nombre LIKE ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + texto + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println(
                "Error al buscar producto por nombre: "
                + e.getMessage()
            );
        }

        return lista;
    }


    public Producto buscarPorId(int id) {

        Producto p = null;

        String sql =
            "SELECT id, nombre, precio, stock " +
            "FROM productos WHERE id=?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                p = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            System.out.println(
                "Error al buscar producto por ID: "
                + e.getMessage()
            );
        }

        return p;
    }

    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}