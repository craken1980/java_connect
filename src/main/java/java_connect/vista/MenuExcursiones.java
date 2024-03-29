
package java_connect.vista;

public class MenuExcursiones extends Menu {
    private static MenuAction[] menu = {
        new ExcursionMenuAction("Añadir Excursion", "crear"),
        new ExcursionMenuAction("Mostrar Excursiones con filtro entre fechas", "filtrar"),
    };
    public MenuExcursiones() {
        super(MenuExcursiones.menu);
    }
}
