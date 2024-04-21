package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java_connect.util.MySQLConection;

public class Excursion {

    private static int codigogenerado;
    private int id;
    private String descripcion;
    private double precio;
    private int dias;
    private Date fecha;
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private Connection conexion;

    public Excursion(String descripcion, Double precio, Date fecha, int dias) {
        this.id = GenerarCodigo();
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.dias = dias;

        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
    }

    private static int GenerarCodigo() {
        codigogenerado++;
        return codigogenerado;
    }

    public int GetId() {

        return this.id;

    }

    public String GetDescripcion() {
        return this.descripcion;
    }

    public void SetDescripcion(String newDescripcion) {
        this.descripcion = newDescripcion;
    }

    public double GetPrecio() {
        return this.precio;
    }

    public void SetPrecio(double newPrecio) {
        this.precio = newPrecio;
    }

    public int GetDias() {
        return this.dias;
    }

    public void SetDias(int newDias) {
        this.dias = newDias;
    }

    public Date GetFecha() {
        return this.fecha;
    }

    public void SetFecha(Date newFecha) {
        this.fecha = newFecha;
    }

    public void aniadirExcursiones() {
        String sql = "Insert into excursiones (descripcion, precio,dias, fecha) values (?,?,?,?)";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, this.descripcion);
            stmt.setDouble(2, this.precio);
            stmt.setInt(3, this.dias);
            stmt.setDate(4, new java.sql.Date(this.fecha.getTime()));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
        //Datos.excursionList.add(ex);
    }

    public static List<Excursion> mostrarExcursiones() {
         List<Excursion> lista = new ArrayList<Excursion>();
        String sql = "SELECT * FROM excursiones";
        try {
            PreparedStatement stmt = MySQLConection.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Excursion ex = new Excursion(rs.getNString("descripcion"), rs.getDouble("precio"), rs.getDate("fecha"), rs.getInt("dias"));
                ex.id = rs.getInt("id");
                lista.add(ex);
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
        return lista;
    }

    public static List<Excursion> FiltrarExcursiones(Date fechaInicio, Date fechaFin) {
        List<Excursion> lista = new ArrayList<Excursion>();
        String sql = "SELECT * FROM excursiones WHERE fecha BETWEEN ? and ?";
        try {
            PreparedStatement stmt = MySQLConection.getConnection().prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Excursion ex = new Excursion(rs.getNString("descripcion"), rs.getDouble("precio"), rs.getDate("fecha"), rs.getInt("dias"));
                ex.id = rs.getInt("id");
                lista.add(ex);
            }
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
        return lista;
    }
    public static boolean existeExcursion(int id) {
        String sql = "SELECT COUNT(1) FROM excursiones WHERE id = ?";
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

    public String toString() {
        return "Nº " + this.id + " " + this.descripcion + " "
                + this.dias + "dias " + this.precio + "€ " + formato.format(this.fecha);
    }
}
