package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java_connect.util.MySQLConection;

public class SocioFederado extends Socio {

    private String nif;
    private Federacion federacion;
    private Connection conexion;

    public SocioFederado(String nif, Federacion federacion, String nombre) {
        super(nombre);
        this.nif = nif;
        this.federacion = federacion;
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
    }

    public String GetNif() {
        return this.nif;
    }

    public void SetNif(String newNif) {
        this.nif = newNif;
    }

    public Federacion GetFederacion() {
        return this.federacion;
    }

    public void SetFederacion(Federacion newFederacion) {
        this.federacion = newFederacion;
    }

    public void altoSocioFederado() {
        int socioId = (int) this.altaSocio();
        String sql = "INSERT INTO SociosFederados (socio_id, nif,federacion_id) values (?,?,(SELECT codigo FROM federaciones WHERE nombre = ?))";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1,socioId);
            stmt.setString(2, this.nif);
            stmt.setString(3,this.federacion.GetNombre());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio federado");
        }
    }

    public static ResultSet mostrarSocioFederado() {
        String sql = "SELECT s.id, s.nombre, s.nSocio, sf.nif, sf.federacion_id, f.nombre as federacion FROM Socios s "
                + "INNER JOIN SociosFederados sf ON s.id = sf.socio_id "
                + "INNER JOIN Federaciones f ON f.codigo = sf.federacion_id ";
        try {
            Statement stmt = MySQLConection.getConnection().createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio federado");
        }
        return null;
    }

    public String toString() {
        return  "Nº " + this.GetNsocio() + " Socio Federado " + this.GetNombre() + " " + this.GetNif();
    }
}
