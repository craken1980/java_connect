package java_connect.modelo;

import javax.persistence.*;

@Entity
@Table(name = "sociosinfantiles")
@PrimaryKeyJoinColumn(name = "socio_id")
public class SocioInfantil extends Socio {

    public SocioInfantil() {
    }

    public SocioInfantil(String nombre) {
        super(nombre);
    }

    // La gestión directa de la base de datos para altas debe ser manejada en la capa de servicio o repositorio.
    // Este método es eliminado de aquí y se recomienda implementarlo en el repositorio correspondiente.

    @Override
    public String toString() {
        return "Nº " + this.getNSocio() + " Socio Infantil " + this.getNombre();
    }

    // Cualquier método adicional para manejar características específicas del socio infantil debe ser también considerado
    // para ser implementado en la capa de servicio o repositorio para mantener las responsabilidades separadas.
}
