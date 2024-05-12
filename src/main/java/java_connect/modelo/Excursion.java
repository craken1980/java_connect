package java_connect.modelo;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "excursiones")
public class Excursion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private double precio;

    @Column(name = "dias")
    private int dias;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Transient
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public Excursion() {
    }

    public Excursion(String descripcion, Double precio, Date fecha, int dias) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.dias = dias;
    }

    // Getters y setters para cada propiedad
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDias() {
        return this.dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Nº " + this.id + " " + this.descripcion + " "
                + this.dias + "dias " + this.precio + "€ " + formato.format(this.fecha);
    }
}
