package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java_connect.util.MySQLConection;

public class Seguro {

    public static enum TipoSeguro {
        BASICO, COMPLETO
    }

    private TipoSeguro tipo;
    private double precio;
    private Connection conexion;

    public Seguro(String tipoSeguro) {
        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
        switch (tipoSeguro.toLowerCase()) {
            case "basico":
                this.precio = 15;
                this.tipo = TipoSeguro.BASICO;
                if (!this.existeSeguro(this.tipo.name())){
                    this.altaSeguro();
                }
                break;
            case "completo":
                this.precio = 20;
                this.tipo = TipoSeguro.COMPLETO;
                if (!this.existeSeguro(this.tipo.name())){
                    this.altaSeguro();
                }
                break;
            default:
                throw new RuntimeException("Seguro no válido");
        }
    }

    public TipoSeguro GetTipo() {
        return this.tipo;
    }

    public void SetTipo(TipoSeguro newTipo) {
        this.tipo = newTipo;
    }

    public double GetPrecio() {
        return this.precio;
    }

    public void SetPrecio(double newPrecio) {
        this.precio = newPrecio;
    }

    public TipoSeguro GetTipoSeguro() {
        return this.tipo;
    }

    public void SetTipoSeguro(TipoSeguro newTipoSeguro) {
        this.tipo = newTipoSeguro;
    }

    public void cancelarSeguro() {

    }

    public void calcularPrecio() {

    }

    public void altaSeguro() {
        String sql = "Insert into Seguros (tipo,precio) values (?,?)";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, this.tipo.name());
            stmt.setDouble(2, this.precio);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el Socio");
        }
    }

    public boolean existeSeguro(String tipo) {
        String sql = "SELECT COUNT(1) FROM seguros WHERE tipo = ?";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            if (rs == null) {
                return false;
            }
            rs.next();
            int numero = rs.getInt(1);
            return numero > 0;
        } catch (SQLException ex) {
            System.out.println("No se pudo ejecutar la consulta para saber si existe la federacion");
        }
        return false;
    }

    public void mostrarSeguro() {

    }

    public void eliminarSeguro() {

    }

    public String toString() {

        return " ";
    }
}
