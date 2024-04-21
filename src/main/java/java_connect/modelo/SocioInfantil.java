package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java_connect.util.MySQLConection;

public class SocioInfantil extends Socio {

    private Connection conexion;

    public SocioInfantil(String nombre) {
        super(nombre);

        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
    }

    public void altaSocioInfantil() {
        int socioId = (int) this.altaSocio();
        String sql = "Insert into sociosinfantiles (socio_id) values (?)";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, socioId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
    }

    public void mostrarSocioInfantil() {

    }

    public String toString() {
        return "Nº " + this.GetNsocio() + " Socio Infantil " + this.GetNombre();
    }
}
