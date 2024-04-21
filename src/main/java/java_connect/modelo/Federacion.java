package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java_connect.util.MySQLConection;

public class Federacion {

    private static int GenerarCodigo() {
        codigogenerado++;
        return codigogenerado;
    }

    private int codigo;
    private String nombre;
    private static int codigogenerado;
    private Connection conexion;

    public Federacion(String nombre) {
        this.codigo = Federacion.GenerarCodigo();
        this.nombre = nombre;
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
        if(!this.existeFederacion(nombre)){
            this.altaFederacion();
        }
    }

    public int GetCodigo() {
        return this.codigo;
    }

    public void SetCodigo(int newCodigo) {
        this.codigo = newCodigo;
    }

    public String GetNombre() {
        return this.nombre;
    }

    public void SetNombre(String newNombre) {
        this.nombre = newNombre;
    }

    public void altaFederacion() {
        String sql = "Insert into federaciones (nombre) values (?)";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, this.nombre);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear la federacion");
        }
    }
    
    

    public boolean existeFederacion(String nombre) {
        String sql = "SELECT COUNT(1) FROM federaciones WHERE nombre = ?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs == null) return false;
            rs.next();
            int numero = rs.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            System.out.println("No se pudo ejecutar la consulta para saber si existe la federacion");
        }
        return false;
    }

    public void mostrarFederacion() {

    }

    public void eliminarFederacion() {

    }

    public String toString() {

        return " ";
    }

}
