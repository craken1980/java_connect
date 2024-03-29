package java_connect.modelo;

public class SocioEstandar extends Socio {

    private String nif;
    private Seguro seguro;

    public SocioEstandar(String nif, Seguro seguro, String nombre) {
        super(nombre);
        this.nif = nif;
        this.seguro = seguro;
    }

    public String GetNif() {
        return this.nif;
    }

    public void setNif(String newNif) {
        this.nif = newNif;
    }

    public Seguro GetSeguro() {
        return this.seguro;
    }

    public void SetSeguro(Seguro newSeguro) {
        this.seguro = newSeguro;
    }

    public void altaSocioEstandar() {

    }

    public void mostrarSocioEstandar() {

    }

    public void modifarSeguro() {

    }

    public String toString() {
        return "Nº " + this.GetNsocio() + " Socio Estandar " 
                + this.GetNombre() + " " + this.GetNif()
                + " Seguro " + this.GetSeguro().GetTipoSeguro().name();
    }
}
