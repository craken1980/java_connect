
package java_connect.vista;

import java.util.List;
import java.util.Scanner;
import java_connect.controlador.Controlador;
import java_connect.modelo.Socio;

public class SocioMenuAction extends MenuAction implements IMenuAction {
    
    private String accion = "";
    
    private final Scanner sc = new Scanner(System.in);
    
    public SocioMenuAction(String titulo, String accion) {
        super(titulo);
        this.accion = accion;
    }

    @Override
    public void Ejecutar() {
        switch(accion){
            case "listar":
                listarSocios();
                break;
            case "eliminar":
                eliminarSocio();
                break;
            case "modificarSeguro":
                modificarSeguroSocio();
                break;
            case "facturamensual":
                
                break;
        }
    }
    
    private void listarSocios(){
        imprimirListaSocios(Controlador.obtenerListaSocios());
    }

    private void imprimirListaSocios(List<Socio> lista) {
        for(Socio socio: lista){
            System.out.println(socio.toString());
        }
    }

    private void eliminarSocio() {
        listarSocios();
        System.out.println("Introduzca el Nº del Socio que desea eliminar:");
        try{
            int nsocio = sc.nextInt();
            sc.nextLine();
            if (!Socio.existeSocio(nsocio)){
                System.out.println("El numero de socio introducido no existe");
            }
            Socio.eliminarSocio(nsocio);
            System.out.println("El socio se ha eliminado correctamente");
        } catch(Exception ex){
            System.out.println("El valor introducido no es válido");
        }
    }
    
    private void modificarSeguroSocio() {
        imprimirListaSocios(Controlador.obtenerListaSociosEstandar());
        System.out.println("Introduzca el Nº del socio al que desea modificarle el seguro:");
        int nsocio = 0;
        try{
            nsocio = sc.nextInt();
            sc.nextLine();
        }catch(Exception ex){
            System.out.println("El Nº de Socio introducido no es válido");
            return;
        }
        if(!Socio.existeSocio(nsocio)){
            System.out.println("El Nº de Socio introducido no existe");
            return;
        }
        System.out.println("Introduzca el nuevo seguro del socio (basico, completo):");
        String seguro = sc.nextLine();
        Controlador.modificarSeguroSocio(nsocio, seguro);
        System.out.println("Se ha modificado correctamente el seguro del socio.");
        
    }
}
