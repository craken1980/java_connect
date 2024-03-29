package java_connect.modelo;

public class Socio {

    private static int codigogenerado;

    private String nombre;
    private int nSocio;

    public Socio(String nombre) {
        this.nombre = nombre;
        this.nSocio = GenerarNSocio();
    }
    
    public String GetNombre() {
        return this.nombre;
    }

    public void SetNombre(String newNombre) {
        this.nombre = newNombre;
    }

    public int GetNsocio() {
        return this.nSocio;
    }
    
    private static int GenerarNSocio(){
        codigogenerado++;
        return codigogenerado;
    }

    public void altaSocio() {

    }

    public void mostrarSocio() {

    }

    public void eliminarSocio() {

    }

    public void mostrarFactura() {

    }

}
