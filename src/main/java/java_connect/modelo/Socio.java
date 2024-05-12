package java_connect.modelo;

import javax.persistence.*;

@Entity
@Table(name = "socios")
@Inheritance(strategy = InheritanceType.JOINED) // Específica el tipo de herencia entre Socio y sus subclases
public abstract class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nSocio", unique = true)
    private int nSocio; // número de socio

    public Socio() {
    }

    public Socio(String nombre) {
        this.nombre = nombre;
        this.nSocio = generarNSocio();
    }

    public Socio(int nSocio, String nombre) {
        this.nombre = nombre;
        this.nSocio = nSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String newNombre) {
        this.nombre = newNombre;
    }

    public int getNSocio() {
        return nSocio;
    }

    private static int generarNSocio() {
        // Esta lógica de generación de nSocio debería ser revisada si depende de la base de datos o algún otro método centralizado
        // Si es importante mantener un control estricto y centralizado, considerar el manejo de esta lógica en la capa de servicio o repositorio
        return (int) (Math.random() * 10000);
    }

    // Métodos relacionados con la persistencia y operaciones de base de datos deben ser manejados en la capa de servicio o repositorio
}
