package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_connect.util.MySQLConection;
import java_connect.vista.*;

public class Main {

    public static void main(String[] args) {
        AppVisual();
    }
    
    public static void AppVisual(){
        GestionSocios gs = new GestionSocios();
        gs.show();
    }
    
    public static void AppConsola() {
        MenuPrincipal menuP = new MenuPrincipal();
        MenuSocios menuS = new MenuSocios();
        MenuInscripciones menuI = new MenuInscripciones();
        MenuExcursiones menuE = new MenuExcursiones();
//        verificarConexion();
        while (true) {
            System.out.println("Menu Principal");
            menuP.imprimir();
            int opcionElegida = menuP.elegirOpcion(true);
            if (opcionElegida == 1) {
                System.out.println("Menu Excursiones");
                menuE.imprimir();
                opcionElegida = menuE.elegirOpcion(true);
                menuE.getMenu()[opcionElegida - 1].Ejecutar();
                Menu.enterParaContinuar();
            } else if (opcionElegida == 2) {
                System.out.println("Menu Socios");
                menuS.imprimir();
                opcionElegida = menuS.elegirOpcion(true);
                menuS.getMenu()[opcionElegida - 1].Ejecutar();
                Menu.enterParaContinuar();
            } else if (opcionElegida == 3) {
                System.out.println("Menu Inscripciones");
                menuI.imprimir();
                opcionElegida = menuI.elegirOpcion(true);
                menuI.getMenu()[opcionElegida - 1].Ejecutar();
                Menu.enterParaContinuar();
            }
        }
    }

//    private static void verificarConexion() {
//        try {
//            Connection conexion = MySQLConection.getConnection();
//            System.out.println("Conexión exitosa");
//
//        } catch (SQLException ex) {
//            System.out.println("No se pudo establecer conexión con la base de datos");
//        }
//    }

}
