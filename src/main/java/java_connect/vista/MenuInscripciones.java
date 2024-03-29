
package java_connect.vista;

public class MenuInscripciones extends Menu {
    private static MenuAction[] menu = {
        new InscripcionMenuAction("Añadir Inscripción", "crear"),
        new InscripcionMenuAction("Eliminar Inscripcion", "eliminar"),
        new InscripcionMenuAction("Mostrar inscripciones con las opciones de filtrar por socio y/o fecha", "filtrar"),
    };
    public MenuInscripciones() {
        super(MenuInscripciones.menu);
    }
}
