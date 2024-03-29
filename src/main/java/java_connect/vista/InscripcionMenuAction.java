
package java_connect.vista;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java_connect.controlador.Controlador;
import java_connect.modelo.Inscripcion;

public class InscripcionMenuAction extends MenuAction implements IMenuAction {
    private String accion = "";
    
    private final Scanner sc = new Scanner(System.in);
    
    public InscripcionMenuAction(String titulo, String accion) {
        super(titulo);
        this.accion = accion;
    }
    
    @Override
    public void Ejecutar() {
        switch(accion){
            case "crear":
                crearInscripcion();
                break;
            case "eliminar":
                eliminarInscripcion();
                break;
            case "filtrar":
                filtrarInscripciones();
                break;
        }
    }

    private void crearInscripcion() {
        MenuAction listarSocios = new SocioMenuAction("", "listar");
        listarSocios.Ejecutar();
        System.out.println("Introduzca el Nº del Socio para el que desea la inscripcion:");
        int nsocio = 0;
        try{
            nsocio = sc.nextInt();
            sc.nextLine();
        } catch(Exception ex){
            sc.nextLine();
            System.out.println("El valor introducido no es válido");
            return;
        }
        if (!Controlador.existeSocio(nsocio)){
            System.out.println("El socio Nº de socio que ha introducido no existe");
            return;
        }
        
        MenuAction listarExcursiones = new ExcursionMenuAction("", "listar");
        listarExcursiones.Ejecutar();
        System.out.println("Introduzca el Nº de la excursion donde se inscribirá el socio:");
        int nexcursion = 0;
        try{
            nexcursion = sc.nextInt();
            sc.nextLine();
        } catch(Exception ex){
            sc.nextLine();
            System.out.println("El valor introducido no es válido");
            return;
        }
        if (!Controlador.existeExcursion(nexcursion)){
            System.out.println("El Nº de excursion que ha introducido no existe");
            return;
        }
        
        Controlador.crearInscripcion(nsocio, nexcursion);
        System.out.println("Se ha creado correctamente la inscripcion");
    }
    
    private void imprimirListaInscripciones(List<Inscripcion> lista) {
        for(Inscripcion inscripcion: lista){
            System.out.println(inscripcion.toString());
        }
    }
    
    private void eliminarInscripcion() {
        imprimirListaInscripciones(Controlador.obtenerListaInscripciones());
        System.out.println("Introduzca el Nº de Inscripcion que desea eliminar:");
        try{
            int ninscripcion = sc.nextInt();
            sc.nextLine();
            if (!Controlador.EliminarInscripcion(ninscripcion)){
                System.out.println("El numero de inscripcion introducido no existe");
            }
            System.out.println("La inscripcion se ha eliminado correctamente");
        } catch(Exception ex){
            System.out.println("El valor introducido no es válido");
        }
    }

    private void filtrarInscripciones() {
        List<Inscripcion> resultado = Controlador.obtenerListaInscripciones();
        System.out.println("Desea filtrar por socio? (s/n)");
        if (sc.nextLine().equals("s")){
            System.out.println("Introduzca el nombre del socio");
            resultado = Controlador.filtarInscripcionesPorSocio(resultado, sc.nextLine());
        }
        System.out.println("Desea filtrar por fecha? (s/n)");
        if (sc.nextLine().equals("s")){
            System.out.println("Introduzca la fecha por la que desea filtrar");
            String fechaString = sc.nextLine();
            Date fecha;
            try{
                fecha = new Date(fechaString);
            }catch(Exception ex){
                System.out.println("La fecha introducida no es valida");
                return;
            }
            resultado = Controlador.filtarInscripcionesPorFecha(resultado, fecha);
        }
        imprimirListaInscripciones(resultado);
    }
}
