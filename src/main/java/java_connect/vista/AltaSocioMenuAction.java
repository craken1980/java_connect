
package java_connect.vista;

import java.util.Scanner;
import java_connect.controlador.Controlador;

public class AltaSocioMenuAction extends MenuAction implements IMenuAction {

    private String tipoSocio = "";
    private Controlador controlador = new Controlador();
    
    public AltaSocioMenuAction(String titulo, String tipo) {
        super(titulo);
        this.tipoSocio = tipo;
    }

    @Override
    public void Ejecutar() {
        switch(this.tipoSocio){
            case "estandar":
                pedirDatosSocioEstandar();
                break;
            case "federado":
                pedirDatosSocioFederado();
                break;
            case "infantil":
                pedirDatosSocioInfantil();
                break;
        }
    }
    
    public void pedirDatosSocioEstandar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del socio:");
        String nombre = sc.nextLine();
        
        System.out.println("Introduzca el nif del socio:");
        String nif = sc.nextLine();
        
        System.out.println("Introduzca el tipo de seguro del socio (basico,completo):");
        String tipoSeguro = sc.nextLine();
        /*if (!controlador.validarSeguro(tipoSeguro)){
            System.out.println("El tipo de seguro introducido no es válido");
            return;
        }*/
        controlador.addSocioEstandar(nif, tipoSeguro, nombre);
        System.out.println("Se ha creado el socio correctamente");
    }
    
    public void pedirDatosSocioFederado(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del socio:");
        String nombre = sc.nextLine();
        
        System.out.println("Introduzca el nif del socio:");
        String nif = sc.nextLine();
        
        System.out.println("Introduzca la federacion a la que pertenece el socio:");
        String federacion = sc.nextLine();
        
        controlador.addSocioFederado(nif, federacion, nombre);
        System.out.println("Se ha creado el socio correctamente");
    }
    
    public void pedirDatosSocioInfantil(){
        
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del socio:");
        String nombre = sc.nextLine();
        
        controlador.addSocioInfantil(nombre);
        System.out.println("Se ha creado el socio correctamente");
    }
    
}
