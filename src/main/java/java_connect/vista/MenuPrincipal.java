
package java_connect.vista;

public class MenuPrincipal extends Menu {
    private static MenuAction[] menu = {
        MenuAction.NoAction("Gestion de Excursiones"),
        MenuAction.NoAction("Gestion de Socios"),
        MenuAction.NoAction("Gestion de Inscripciones"),
    };
    public MenuPrincipal() {
        super(MenuPrincipal.menu);
    }
}
