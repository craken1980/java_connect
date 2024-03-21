package principal;
import java.util.Date;

public class Excursion {
	private String id;
	private String descripcion;
	private double precio;
	private int dias;
	private Date fecha;
	
	public String GetId() {
		
		return this.id;
		
	}
	public void SetId(String newId) {
		this.id = newId;
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
	public void aniadirExcursiones() {
		
	}
	public void mostarExcursiones() {
		
	}
	public String toString() {
		
		return " ";
	}
}

