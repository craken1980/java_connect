package java_connect.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Excursion {
    private static int codigogenerado;
    private int id;
    private String descripcion;
    private double precio;
    private int dias;
    private Date fecha;
    private final SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    public Excursion(String descripcion, Double precio, Date fecha, int dias) {
        this.id = GenerarCodigo();
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.dias = dias;
    }
    
    
    private static int GenerarCodigo(){
        codigogenerado++;
        return codigogenerado;
    }

    public int GetId() {

        return this.id;

    }

    public String GetDescripcion() {
        return this.descripcion;
    }

    public void SetDescripcion(String newDescripcion) {
        this.descripcion = newDescripcion;
    }

    public double GetPrecio() {
        return this.precio;
    }

    public void SetPrecio(double newPrecio) {
        this.precio = newPrecio;
    }

    public int GetDias() {
        return this.dias;
    }

    public void SetDias(int newDias) {
        this.dias = newDias;
    }

    public Date GetFecha() {
        return this.fecha;
    }

    public void SetFecha(Date newFecha) {
        this.fecha = newFecha;
    }

    public void aniadirExcursiones(Excursion ex) {
        Datos.excursionList.add(ex);
    }

    public List<Excursion> mostrarExcursiones() {
        return Datos.excursionList;
    }

    public String toString() {
        return "Nº " + this.id + " " + this.descripcion + " " 
                + this.dias + "dias " + this.precio + "€ " + formato.format(this.fecha);
    }
}
