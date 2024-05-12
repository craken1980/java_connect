
package java_connect.test;

import java_connect.controlador.Controlador;
import java_connect.modelo.Federacion;
import java_connect.modelo.Inscripcion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Prueba {
    
    private static Controlador controlador = new Controlador();
    public Prueba() {
    }

    @BeforeAll
    public static void setUpClass() {
        String nif = "1234567B";
        String tipoSeguro = "basico";
        String nombre = "Luisa";
        controlador.addSocioEstandar(nif, tipoSeguro, nombre);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void altaSocioEstandar() {
        String nif = "1234567B";
        String tipoSeguro = "slñdkfjaslñkf";
        String nombre = "Pepe";
        try{
            controlador.addSocioEstandar(nif, tipoSeguro, nombre);
        }catch(RuntimeException e){
            assertEquals("Seguro no válido", e.getMessage());
        }
    }
    
//    @Test
//    public void existeSocio() {
//        boolean existe = Controlador.existeSocio(1);
//        assertEquals(true, existe);
//    }
//    
//    @Test
//    public void noExisteSocio() {
//        boolean existe = Controlador.existeSocio(58);
//        assertEquals(false, existe);
//    }
}
