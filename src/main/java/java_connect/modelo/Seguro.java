package java_connect.modelo;

public class Seguro {

    public static enum TipoSeguro {
        BASICO, COMPLETO
    }

    private TipoSeguro tipo;
    private double precio;

    public Seguro(String tipoSeguro) {
        switch(tipoSeguro){
            case "basico":
                this.precio = 15;
                this.tipo = TipoSeguro.BASICO;
                break;
            case "completo":
                this.precio = 20;
                this.tipo = TipoSeguro.COMPLETO;
                break;
            default:
                throw new RuntimeException("Seguro no válido");
        }
    }

    public TipoSeguro GetTipo() {
        return this.tipo;
    }

    public void SetTipo(TipoSeguro newTipo) {
        this.tipo = newTipo;
    }

    public double GetPrecio() {
        return this.precio;
    }

    public void SetPrecio(double newPrecio) {
        this.precio = newPrecio;
    }

    public TipoSeguro GetTipoSeguro() {
        return this.tipo;
    }

    public void SetTipoSeguro(TipoSeguro newTipoSeguro) {
        this.tipo = newTipoSeguro;
    }

    public void cancelarSeguro() {

    }

    public void calcularPrecio() {

    }

    public void altaSeguro() {

    }

    public void mostrarSeguro() {

    }

    public void eliminarSeguro() {

    }

    public String toString() {

        return " ";
    }
}
