package main;

import java_connect.vista.*;

public class Main {

    public static void main(String[] args) {
        MenuPrincipal menuP = new MenuPrincipal();
        MenuSocios menuS = new MenuSocios();
        MenuInscripciones menuI = new MenuInscripciones();
        MenuExcursiones menuE = new MenuExcursiones();

        while(true){
            System.out.println("Menu Principal");
            menuP.imprimir();
            int opcionElegida = menuP.elegirOpcion(true);
            if(opcionElegida == 1){
                System.out.println("Menu Excursiones");
                menuE.imprimir();
                opcionElegida = menuE.elegirOpcion(true);
                menuE.getMenu()[opcionElegida - 1].Ejecutar();
                Menu.enterParaContinuar();
            }
            else if(opcionElegida == 2){
                System.out.println("Menu Socios");
                menuS.imprimir();
                opcionElegida = menuS.elegirOpcion(true);
                menuS.getMenu()[opcionElegida - 1].Ejecutar();
                Menu.enterParaContinuar();
            }
            else if(opcionElegida == 3){
                System.out.println("Menu Inscripciones");
                menuI.imprimir();
                opcionElegida = menuI.elegirOpcion(true);
                menuI.getMenu()[opcionElegida - 1].Ejecutar();
                Menu.enterParaContinuar();
            }
        }
    }

}
