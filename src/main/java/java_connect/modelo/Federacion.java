package java_connect.modelo;

import javax.persistence.*;

@Entity
@Table(name = "federaciones")
public class Federacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "nombre", unique = true)
    private String nombre;

    public Federacion() {
    }

    public Federacion(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int newCodigo) {
        this.codigo = newCodigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String newNombre) {
        this.nombre = newNombre;
    }

    @Override
    public String toString() {
        return "Codigo: " + this.codigo + ", Nombre: " + this.nombre;
    }

    // Métodos de negocio como 'altaFederacion' o 'existeFederacion' deben moverse a una capa de servicio o repositorio.
}
