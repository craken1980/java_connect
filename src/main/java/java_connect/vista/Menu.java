
package java_connect.vista;

import java.util.Scanner;

public abstract class Menu {
    private MenuAction[] menu;
    
    public Menu(MenuAction[] menu) {
        this.menu = menu;
    }

    public MenuAction[] getMenu() {
        return menu;
    }
    
    public void imprimir() {
        int index = 1;
        for (MenuAction opcion : menu) {
            System.out.printf("%d.- %s\n", index, opcion.getTitle());
            index++;
        }
    }
    
    public static void enterParaContinuar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----- Presione Enter para continuar -----");
        sc.nextLine();
    }
    
    public int elegirOpcion(boolean insistir){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Elige una opción del menu");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion <= 0 || opcion > menu.length){
                    System.out.println("La opcion elegida no es válida");
                    continue;
                }
                return opcion;
            }catch(Exception ex) {
                sc.nextLine();
                System.out.println("La opcion elegida no es válida");
            }
        } while (insistir);
        return opcion;
    }
}
