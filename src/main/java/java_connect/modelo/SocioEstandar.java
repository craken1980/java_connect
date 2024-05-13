package java_connect.modelo;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "sociosestandar")
@Transactional
@PrimaryKeyJoinColumn(name = "socio_id")
public class SocioEstandar extends Socio {

    @Column(name = "nif")
    private String nif;

    @ManyToOne
    @JoinColumn(name = "seguro_id", referencedColumnName = "id")
    private Seguro seguro;

    public SocioEstandar() {
    }

    public SocioEstandar(String nif, Seguro seguro, String nombre) {
        super(nombre);
        this.nif = nif;
        this.seguro = seguro;
    }

    public SocioEstandar(int id, String nif, Seguro seguro, String nombre) {
        super(id, nombre);
        this.nif = nif;
        this.seguro = seguro;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String newNif) {
        this.nif = newNif;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro newSeguro) {
        this.seguro = newSeguro;
    }

    @Override
    public String toString() {
        return "Nº " + this.getNSocio() + " Socio Estandar " 
                + this.getNombre() + " " + this.nif
                + " Seguro " + (this.seguro != null ? this.seguro.getTipo().name() : "N/A");
    }

    // Eliminamos los métodos que manejan directamente la base de datos como altaSocioEstandar y modifarSeguro
    // Estos deberían ser manejados por la capa de servicio o el repositorio.
}
