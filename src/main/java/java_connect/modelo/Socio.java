package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java_connect.util.MySQLConection;

public abstract class Socio {

    private static int codigogenerado;

    private String nombre;
    private int nSocio;
    private Connection conexion = null;

    public Socio(String nombre) {
        this.nombre = nombre;
        this.nSocio = GenerarNSocio();
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
    }

    public Socio(int nSocio, String nombre) {
        this.nombre = nombre;
        this.nSocio = nSocio;
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
    }

    public String GetNombre() {
        return this.nombre;
    }

    public void SetNombre(String newNombre) {
        this.nombre = newNombre;
    }

    public int GetNsocio() {
        return this.nSocio;
    }

    private static int GenerarNSocio() {
        codigogenerado++;
        return codigogenerado;
    }

    public long altaSocio() {
        String sql = "Insert into socios (nombre,nSocio) values (?,?)";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nombre);
            stmt.setInt(2, this.nSocio);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el Socio");
            return -1;
        }

    }

    public static void eliminarSocio(int socio_id) {
        String[] queries = {
            "DELETE FROM sociosInfantiles WHERE socio_id = ?;",
            "DELETE FROM SociosFederados WHERE socio_id = ?;",
            "DELETE FROM sociosEstandar WHERE socio_id = ?;",
            "DELETE FROM socios WHERE id = ?;"
        };
        try {
            for (String sql : queries){
                PreparedStatement stmt = MySQLConection.getConnection().prepareStatement(sql);
                stmt.setInt(1, socio_id);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el Socio");
        }
    }

    public void mostrarSocio() {

    }

    public void eliminarSocio() {

    }

    public static boolean existeSocio(int id) {
        String sql = "SELECT COUNT(1) FROM socios WHERE id = ?";
        try {
            PreparedStatement stmt = MySQLConection.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs == null) {
                return false;
            }
            rs.next();
            int numero = rs.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            System.out.println("No se pudo ejecutar la consulta para saber si existe el socio");
        }
        return false;
    }

}
