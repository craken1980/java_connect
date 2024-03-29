package java_connect.modelo;

public class SocioFederado extends Socio {

    private String nif;
    private Federacion federacion;

    public SocioFederado(String nif, Federacion federacion, String nombre) {
        super(nombre);
        this.nif = nif;
        this.federacion = federacion;
    }

    public String GetNif() {
        return this.nif;
    }

    public void SetNif(String newNif) {
        this.nif = newNif;
    }

    public Federacion GetFederacion() {
        return this.federacion;
    }

    public void SetFederacion(Federacion newFederacion) {
        this.federacion = newFederacion;
    }

    public void altoSocioFederado() {

    }

    public void mostrarSocioFederado() {

    }

    public String toString() {
        return  "Nº " + this.GetNsocio() + " Socio Federado " + this.GetNombre() + " " + this.GetNif();
    }
}
