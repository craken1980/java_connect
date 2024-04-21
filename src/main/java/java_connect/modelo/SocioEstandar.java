package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java_connect.util.MySQLConection;

public class SocioEstandar extends Socio {

    private String nif;
    private Seguro seguro;
    private Connection conexion;
    public SocioEstandar(String nif, Seguro seguro, String nombre) {
        super(nombre);
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
        this.nif = nif;
        this.seguro = seguro;
    }
    public SocioEstandar(int id, String nif, Seguro seguro, String nombre) {
        super(id, nombre);
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
        this.nif = nif;
        this.seguro = seguro;
    }

    public String GetNif() {
        return this.nif;
    }

    public void setNif(String newNif) {
        this.nif = newNif;
    }

    public Seguro GetSeguro() {
        return this.seguro;
    }

    public void SetSeguro(Seguro newSeguro) {
        this.seguro = newSeguro;
        String sql = "UPDATE sociosestandar SET seguro_id = (SELECT id FROM seguros WHERE tipo = ?) WHERE socio_id = ?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1,newSeguro.GetTipo().name());
            stmt.setInt(2,this.GetNsocio());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
    }

    public void altaSocioEstandar() {
        int socioId = (int) this.altaSocio();
        String sql = "Insert into sociosestandar (socio_id, nif,seguro_id) values (?,?,(SELECT id FROM seguros WHERE tipo = ?))";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1,socioId);
            stmt.setString(2, this.nif);
            stmt.setString(3,this.seguro.GetTipo().name());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
    }

    public static SocioEstandar obtenerSocioEstandar(int id) {
        String sql = "SELECT s.id, s.nombre, s.nSocio, se.nif, se.seguro_id, seg.tipo FROM Socios s "
                + "INNER JOIN SociosEstandar se ON s.id = se.socio_id "
                + "INNER JOIN Seguros seg ON seg.id = se.seguro_id WHERE s.id = ? ";
        try {
            PreparedStatement stmt = MySQLConection.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs =  stmt.executeQuery();
             while(rs.next()){
                Seguro seg = new Seguro(rs.getNString("tipo"));
                return new SocioEstandar(rs.getInt("id"),rs.getNString("nif"), seg, rs.getNString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
        return null;
    }

    public static ResultSet mostrarSocioEstandar() {
        String sql = "SELECT s.id, s.nombre, s.nSocio, se.nif, se.seguro_id, seg.tipo FROM Socios s "
                + "INNER JOIN SociosEstandar se ON s.id = se.socio_id "
                + "INNER JOIN Seguros seg ON seg.id = se.seguro_id ";
        try {
            Statement stmt = MySQLConection.getConnection().createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
        return null;
    }

    public void modifarSeguro() {

    }

    public String toString() {
        return "Nº " + this.GetNsocio() + " Socio Estandar " 
                + this.GetNombre() + " " + this.GetNif()
                + " Seguro " + this.GetSeguro().GetTipoSeguro().name();
    }
}
