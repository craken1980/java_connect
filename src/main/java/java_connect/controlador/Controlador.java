
package java_connect.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java_connect.modelo.Datos;
import java_connect.modelo.Excursion;
import java_connect.modelo.Federacion;
import java_connect.modelo.Inscripcion;
import java_connect.modelo.Seguro;
import java_connect.modelo.Socio;
import java_connect.modelo.SocioEstandar;
import java_connect.modelo.SocioFederado;
import java_connect.modelo.SocioInfantil;

public class Controlador {
    public static void AddSocioEstandar(String nif, String tipoSeguro, String nombre) {
        Seguro seguro = new Seguro(tipoSeguro);
        SocioEstandar socioE = new SocioEstandar (nif, seguro, nombre);
        Datos.socioList.add(socioE);
    }
    
    
    public static boolean ValidarSeguro(String tipoSeguro) {
        if(tipoSeguro.equals("basico") || tipoSeguro.equals("completo")){
            return true;
        }
        return false;
    }
    
    public static void AddSocioFederado(String nif, String federacion, String nombre) {
        Federacion fede = null;
        for(Federacion fed : Datos.federacionList){
            if(fed.GetNombre().equals(federacion)){
                fede = fed;
            }
        }
        if (fede == null) fede = new Federacion(federacion);
        
        SocioFederado socioF = new SocioFederado (nif, fede, nombre);
        Datos.socioList.add(socioF);
    }
    
    public static void AddSocioInfantil(String nombre) {
        SocioInfantil socioI = new SocioInfantil (nombre);
        Datos.socioList.add(socioI);
    }
    
    public static List<Socio> obtenerListaSocios(){
        return Datos.socioList;
    }

    public static boolean EliminarSocio(int nsocio) {
        boolean encontrado = false;
        for(Socio socio : Datos.socioList){
            if (socio.GetNsocio() == nsocio){
                encontrado = true;
                Datos.socioList.remove(socio);
                break;
            }
        }
        return encontrado;
    }

    public static List<Socio> obtenerListaSociosEstandar() {
        List<Socio> filtrado = new ArrayList<Socio>();
        for(Socio socio: Datos.socioList){
            if(socio instanceof SocioEstandar){
                filtrado.add(socio);
            }
        }
        return filtrado;
    }

    public static boolean existeSocio(int nsocio) {
        for(Socio socio: Datos.socioList){
            if(socio.GetNsocio() == nsocio){
                return true;
            }
        }
        return false;
    }

    private static Socio obtenerSocio(int nsocio) {
        List<Socio> filtrado = new ArrayList<Socio>();
        for(Socio socio: Datos.socioList){
            if(socio.GetNsocio() == nsocio){
                return socio;
            }
        }
        return null;
    }
    
    public static void modificarSeguroSocio(int nsocio, String seguro){
        SocioEstandar socio = (SocioEstandar) obtenerSocio(nsocio);
        socio.SetSeguro(new Seguro(seguro));
            
    }

    public static void crearExcursion(String descripcion, Double precio, Date fecha, int dias) {
        Excursion excursion = new Excursion(descripcion, precio, fecha, dias);
        Datos.excursionList.add(excursion);
    }
    
    public static List<Excursion> filtrarExcursiones(Date fechaInicio, Date fechaFin){
        List<Excursion> filtrada = new ArrayList<Excursion>();
        for(Excursion excursion : Datos.excursionList){
            Date fechaExcursion = excursion.GetFecha();
            if (fechaExcursion.after(fechaInicio) && fechaExcursion.before(fechaFin)){
                filtrada.add(excursion);
            }
        }
        return filtrada;
    }

    public static List<Excursion> obtenerListaExcursiones() {
        return Datos.excursionList;
    }

    public static List<Inscripcion> obtenerListaInscripciones() {
        return Datos.inscripcionList;
    }
    
    

    public static boolean existeExcursion(int nexcursion) {
        for(Excursion excursion: Datos.excursionList){
            if(excursion.GetId()== nexcursion){
                return true;
            }
        }
        return false;
    }

    private static Excursion obtenerExcursion(int nexcursion) {
        List<Excursion> filtrado = new ArrayList<Excursion>();
        for(Excursion excursion: Datos.excursionList){
            if(excursion.GetId()== nexcursion){
                return excursion;
            }
        }
        return null;
    }

    public static void crearInscripcion(int nsocio, int nexcursion) {
        Socio socio = obtenerSocio(nsocio);
        Excursion excursion = obtenerExcursion(nexcursion);
        Inscripcion inscripcion = new Inscripcion(socio, excursion);
        Datos.inscripcionList.add(inscripcion);
    }

    public static boolean EliminarInscripcion(int ninscripcion) {
        boolean encontrado = false;
        for(Inscripcion inscripcion : Datos.inscripcionList){
            if (inscripcion.getInscripcion() == ninscripcion){
                encontrado = true;
                Datos.inscripcionList.remove(inscripcion);
                break;
            }
        }
        return encontrado;
    }
    
    public static List<Inscripcion> filtarInscripcionesPorSocio(List<Inscripcion> lista, String nombre){
        List<Inscripcion> filtrado = new ArrayList<Inscripcion>();
        for(Inscripcion inscripcion : lista){
            if (inscripcion.getSocio().GetNombre().contains(nombre)){
                filtrado.add(inscripcion);
            }
        }
        return filtrado;
    }
    
    public static List<Inscripcion> filtarInscripcionesPorFecha(List<Inscripcion> lista, Date fecha){
        List<Inscripcion> filtrado = new ArrayList<Inscripcion>();
        for(Inscripcion inscripcion : lista){
            if (inscripcion.getExcursion().GetFecha().equals(fecha)){
                filtrado.add(inscripcion);
            }
        }
        return filtrado;
    }
}
