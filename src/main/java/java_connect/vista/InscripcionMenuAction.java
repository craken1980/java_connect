
package java_connect.vista;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java_connect.controlador.Controlador;
import java_connect.modelo.Excursion;
import java_connect.modelo.Inscripcion;
import java_connect.modelo.Socio;

public class InscripcionMenuAction extends MenuAction implements IMenuAction {
    private String accion = "";
    
    private final Scanner sc = new Scanner(System.in);
    
    private Controlador controlador = new Controlador();
    
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
//        if (!Socio.existeSocio(nsocio)){
//            System.out.println("El socio Nº de socio que ha introducido no existe");
//            return;
//        }
        
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
//        if (!Excursion.existeExcursion(nexcursion)){
//            System.out.println("El Nº de excursion que ha introducido no existe");
//            return;
//        }
        
        controlador.crearInscripcion(nsocio, nexcursion);
        System.out.println("Se ha creado correctamente la inscripcion");
    }
    
    private void imprimirListaInscripciones(List<Inscripcion> lista) {
        for(Inscripcion inscripcion: lista){
            System.out.println(inscripcion.toString());
        }
    }
    
    private void eliminarInscripcion() {
        imprimirListaInscripciones(controlador.obtenerListaInscripciones(null, null));
        System.out.println("Introduzca el Nº de Inscripcion que desea eliminar:");
        try{
            int ninscripcion = sc.nextInt();
            sc.nextLine();
            if (!controlador.eliminarInscripcion(ninscripcion)){
                System.out.println("El numero de inscripcion introducido no existe");
            }
            System.out.println("La inscripcion se ha eliminado correctamente");
        } catch(Exception ex){
            System.out.println("El valor introducido no es válido");
        }
    }

    private void filtrarInscripciones() {
        System.out.println("Desea filtrar por socio? (s/n)");
        String nombre = null;
        Date fecha = null;
        if (sc.nextLine().equals("s")){
            System.out.println("Introduzca el nombre del socio");
            nombre = sc.nextLine();
        }
        System.out.println("Desea filtrar por fecha? (s/n)");
        if (sc.nextLine().equals("s")){
            System.out.println("Introduzca la fecha por la que desea filtrar");
            String fechaString = sc.nextLine();
            try{
                fecha = new Date(fechaString);
            }catch(Exception ex){
                System.out.println("La fecha introducida no es valida");
                return;
            }
        }
        imprimirListaInscripciones(controlador.obtenerListaInscripciones(nombre, fecha));
    }
}
