package java_connect.modelo;

public class Inscripcion {

    private static int codigogenerado;
    private int inscripcion;
    private Socio socio;
    private Excursion excursion;

    public Inscripcion(Socio socio, Excursion excursion) {
        this.inscripcion = GenerarCodigo();
        this.socio = socio;
        this.excursion = excursion;
    }

    private static int GenerarCodigo(){
        codigogenerado++;
        return codigogenerado;
    }
    
    public Socio getSocio() {
        return socio;
    }

    public int getInscripcion() {
        return inscripcion;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void altaInscripcion() {

    }

    public void mostrarInscripcion() {

    }

    public void eliminarInscripcion() {

    }

    public String toString() {
        return "Nº " + this.inscripcion + " " + this.socio.toString() + "\n\t-" + this.excursion.toString();
    }

}
