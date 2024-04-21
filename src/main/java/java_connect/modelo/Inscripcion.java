package java_connect.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java_connect.util.MySQLConection;

public class Inscripcion {

    private static int codigogenerado;
    private int inscripcion;
    private Socio socio;
    private Excursion excursion;
    private Connection conexion;

    public Inscripcion(Socio socio, Excursion excursion) {
        this.inscripcion = GenerarCodigo();
        this.socio = socio;
        this.excursion = excursion;

        try {
            this.conexion = MySQLConection.getConnection();
        } catch (SQLException ex) {
        }
    }

    private static int GenerarCodigo() {
        codigogenerado++;
        return codigogenerado;
    }

    public Socio getSocio() {
        return socio;
    }

    public int getInscripcion() {
        return inscripcion;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void altaInscripcion(int socio_id, int excursion_id) {
        String sql = "Insert into inscripciones (socio_id, excursion_id) values (?,?)";
        try {
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, socio_id);
            stmt.setInt(2, excursion_id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("No se pudo crear el socio estandar");
        }
    }

    public static List<Inscripcion> mostrarInscripcion(String nombre, Date fecha) {
        List<Inscripcion> lista = new ArrayList<Inscripcion>();
        String sql = "SELECT * FROM inscripciones i INNER JOIN socios s ON i.socio_id = s.id "
                + "INNER JOIN excursiones e ON e.id = i.excursion_id "
                + "LEFT JOIN SociosEstandar se ON se.socio_id = s.id "
                + "LEFT JOIN SociosFederados sf ON sf.socio_id = s.id "
                + "LEFT JOIN SociosInfantiles si ON si.socio_id = s.id "
                + "LEFT JOIN Seguros seg ON seg.id = se.seguro_id "
                + "LEFT JOIN Federaciones f ON f.codigo = sf.federacion_id "
                + "WHERE s.nombre = IFNULL(?, s.nombre) AND e.fecha = IFNULL(?, e.fecha)";

        try {
            PreparedStatement stmt = MySQLConection.getConnection().prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setDate(2, fecha != null ? new java.sql.Date(fecha.getTime()) : null);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Socio socio = null;
                rs.getInt("se.socio_id");
                if (!rs.wasNull()) {
                    Seguro seg = new Seguro(rs.getNString("seg.tipo"));
                    socio = new SocioEstandar(rs.getNString("se.nif"), seg, rs.getNString("s.nombre"));
                } else {
                    rs.getInt("sf.socio_id");
                    if (!rs.wasNull()) {
                        Federacion fed = new Federacion(rs.getNString("f.nombre"));
                        socio = new SocioFederado(rs.getNString("sf.nif"), fed, rs.getNString("s.nombre"));
                    } else {
                        rs.getInt("sf.socio_id");
                        if (!rs.wasNull()) {
                            socio = new SocioInfantil(rs.getNString("s.nombre"));
                        }
                    }
                }

                Excursion ex = new Excursion(rs.getNString("e.descripcion"), rs.getDouble("e.precio"), rs.getDate("e.fecha"), rs.getInt("e.dias"));
                Inscripcion i = new Inscripcion(socio, ex);
                i.inscripcion = rs.getInt("i.inscripcion");
                lista.add(i);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener las inscripciones: " + ex.getMessage());
        }

        return lista;
    }

    public void eliminarInscripcion() {

    }

    public String toString() {
        return "-Inscripcion " + this.inscripcion + " " + this.socio.toString() + "\n\t-" + this.excursion.toString();
    }

}
