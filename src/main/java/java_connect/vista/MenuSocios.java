
package java_connect.vista;

public class MenuSocios extends Menu {
    private static MenuAction[] menu = {
        new AltaSocioMenuAction("Añadir Socio Estándar", "estandar"), 
        new SocioMenuAction("Modificar tipo de seguro de un socio estándar", "modificarSeguro"),
        new AltaSocioMenuAction("Añadir Socio Federado", "federado"),
        new AltaSocioMenuAction("Añadir Socio Infantil", "infantil"),
        new SocioMenuAction("Eliminar un socio","eliminar"),
        new SocioMenuAction("Mostrar Socios", "listar"),
        MenuAction.NoAction("Mostrar Fatura mensual por socios"),
    };

    public MenuSocios() {
        super(MenuSocios.menu);
    }
    
}
