package java_connect.modelo;

public class Federacion {

    private static int GenerarCodigo() {
        codigogenerado++;
        return codigogenerado;
    }

    private int codigo;
    private String nombre;
    private static int codigogenerado;

    public Federacion(String nombre) {
        this.codigo = Federacion.GenerarCodigo();
        this.nombre = nombre;
    }

    public int GetCodigo() {
        return this.codigo;
    }

    public void SetCodigo(int newCodigo) {
        this.codigo = newCodigo;
    }

    public String GetNombre() {
        return this.nombre;
    }

    public void SetNombre(String newNombre) {
        this.nombre = newNombre;
    }

    public void altaFederacion() {

    }

    public void mostrarFederacion() {

    }

    public void eliminarFederacion() {

    }

    public String toString() {

        return " ";
    }

}
