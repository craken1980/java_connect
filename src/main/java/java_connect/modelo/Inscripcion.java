package java_connect.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inscripcionId;

    @ManyToOne
    @JoinColumn(name = "socio_id", referencedColumnName = "id")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "excursion_id", referencedColumnName = "id")
    private Excursion excursion;

    public Inscripcion() {
    }

    public Inscripcion(Socio socio, Excursion excursion) {
        this.socio = socio;
        this.excursion = excursion;
    }

    public int getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(int inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    @Override
    public String toString() {
        return "-Inscripcion " + this.inscripcionId + " " + this.socio.toString() + "\n\t-" + this.excursion.toString();
    }

    // Nota: Las funciones como altaInscripcion deberían ser manejadas por la capa de servicio o el repositorio.
}
