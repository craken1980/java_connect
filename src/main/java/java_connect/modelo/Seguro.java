package java_connect.modelo;

import javax.persistence.*;

@Entity
@Table(name = "seguros")
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoSeguro tipo;

    @Column(name = "precio")
    private double precio;

    public enum TipoSeguro {
        BASICO, COMPLETO
    }

    public Seguro() {
    }

    public Seguro(String tipoSeguro) {
        switch (tipoSeguro.toLowerCase()) {
            case "basico":
                this.precio = 15;
                this.tipo = TipoSeguro.BASICO;
                break;
            case "completo":
                this.precio = 20;
                this.tipo = TipoSeguro.COMPLETO;
                break;
            default:
                throw new RuntimeException("Tipo de seguro no válido");
        }
    }

    // Getters y Setters
    public TipoSeguro getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoSeguro newTipo) {
        this.tipo = newTipo;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double newPrecio) {
        this.precio = newPrecio;
    }

    @Override
    public String toString() {
        return "Seguro{" +
               "id=" + id +
               ", tipo=" + tipo +
               ", precio=" + precio +
               '}';
    }

    // Métodos como 'altaSeguro' y 'existeSeguro' deberían ser gestionados por una capa de servicio o repositorio.
}
