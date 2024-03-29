
package java_connect.vista;

public abstract class MenuAction implements IMenuAction {

    private String title = "";
    
    public static MenuAction NoAction(String title) {
        return new NoAction(title);
    }
    
    public String getTitle() {
        return this.title;
    }

    public MenuAction(String title) {
        this.title = title;
    }
    
}

class NoAction extends MenuAction {

    public NoAction(String title) {
        super(title);
    }

    @Override
    public void Ejecutar() {
    }
    
}
