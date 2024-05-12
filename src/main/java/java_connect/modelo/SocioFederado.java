package java_connect.modelo;

import javax.persistence.*;

@Entity
@Table(name = "sociosfederados")
@PrimaryKeyJoinColumn(name = "socio_id")
public class SocioFederado extends Socio {

    @Column(name = "nif")
    private String nif;

    @ManyToOne
    @JoinColumn(name = "federacion_id", referencedColumnName = "codigo")
    private Federacion federacion;

    public SocioFederado() {
    }

    public SocioFederado(String nif, Federacion federacion, String nombre) {
        super(nombre);
        this.nif = nif;
        this.federacion = federacion;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String newNif) {
        this.nif = newNif;
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion newFederacion) {
        this.federacion = newFederacion;
    }

    @Override
    public String toString() {
        return "Nº " + this.getNSocio() + " Socio Federado " + this.getNombre() + " NIF: " + this.nif + 
               (federacion != null ? " Federación: " + federacion.getNombre() : " Sin Federación");
    }

    // Los métodos que manejan la base de datos, como altoSocioFederado, deben trasladarse a la capa de servicio o repositorio.
}
