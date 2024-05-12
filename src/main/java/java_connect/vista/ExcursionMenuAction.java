
package java_connect.vista;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java_connect.controlador.Controlador;
import java_connect.modelo.Excursion;

public class ExcursionMenuAction extends MenuAction implements IMenuAction {
    private String accion = "";
    
    private final Scanner sc = new Scanner(System.in);
    private Controlador controlador = new Controlador();
    
    public ExcursionMenuAction(String titulo, String accion) {
        super(titulo);
        this.accion = accion;
    }
    
    @Override
    public void Ejecutar() {
        switch(accion){
            case "crear":
                crearExcursion();
                break;
            case "filtrar":
                filtrarExcursiones();
                break;
            case "listar":
                mostrarExcursiones();
                break;
        }
    }

    private void crearExcursion() {
        System.out.println("Introduzca la descripcion de la excursion");
        String descripcion = sc.nextLine();
        
        System.out.println("Introduzca los dias que durara la excursion");
        int dias;
        try{
            dias = sc.nextInt();
            sc.nextLine();
        }catch(Exception ex){
            sc.nextLine();
            System.out.println("El precio introducido no es valido");
            return;
        }
        
        System.out.println("Introduzca el precio de la excursion");
        Double precio;
        try{
            precio = sc.nextDouble();
            sc.nextLine();
        }catch(Exception ex){
            sc.nextLine();
            System.out.println("El precio introducido no es valido");
            return;
        }
        
        System.out.println("Introduzca la fecha de la excursion");
        String fechaString = sc.nextLine();
        Date fecha;
        try{
            fecha = new Date(fechaString);
        }catch(Exception ex){
            System.out.println("La fecha introducida no es valida");
            return;
        }
        
        controlador.crearExcursion(descripcion, precio, fecha, dias);
        System.out.println("Se ha creado la excursion correctamente");
    }
    
    private void imprimirListaExcursiones(List<Excursion> lista) {
        for(Excursion excursion: lista){
            System.out.println(excursion.toString());
        }
    }

    private void filtrarExcursiones() {
        System.out.println("Introduzca la fecha de inicio por la que desea filtrar");
        String fechaInicioString = sc.nextLine();
        Date fechaInicio;
        try{
            fechaInicio = new Date(fechaInicioString);
        }catch(Exception ex){
            System.out.println("La fecha introducida no es valida");
            return;
        }
        
        System.out.println("Introduzca la fecha de fin por la que desea filtrar");
        String fechaFinString = sc.nextLine();
        Date fechaFin;
        try{
            fechaFin = new Date(fechaFinString);
        }catch(Exception ex){
            System.out.println("La fecha de fin introducida no es valida");
            return;
        }
        
        if(!fechaInicio.before(fechaFin)){
            System.out.println("La fecha de inicio debe ser anterior a la fecha de fin");
            return;
        }
        
        imprimirListaExcursiones(controlador.filtrarExcursiones(fechaInicio, fechaFin));
        
    }

    private void mostrarExcursiones() {
        imprimirListaExcursiones(controlador.obtenerListaExcursiones());
    }
    
}
