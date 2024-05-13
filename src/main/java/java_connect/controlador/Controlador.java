package java_connect.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java_connect.modelo.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controlador {

    private EntityManager entityManager;

    public Controlador() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        this.entityManager = factory.createEntityManager();
    }

    public void addSocioEstandar(String nif, String tipoSeguro, String nombre) {
        Seguro seguro = new Seguro(tipoSeguro);
        SocioEstandar socioEstandar = new SocioEstandar(nif, seguro, nombre);
        entityManager.getTransaction().begin(); // Inicia la transacción
        try {
            entityManager.persist(seguro); // Persiste el seguro primero
            entityManager.persist(socioEstandar); // Luego persiste el socio
            entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback en caso de error
            throw e; // Opcionalmente relanza la excepción para manejo externo
        }
    }

    public boolean validarSeguro(String tipoSeguro) {
        return tipoSeguro.equals("basico") || tipoSeguro.equals("completo");
    }

    public void addSocioFederado(String nif, String federacionNombre, String nombre) {
        Federacion federacion = new Federacion(federacionNombre);
//        if (!entityManager.contains(federacion)) {
//            entityManager.persist(federacion);
//        }
        SocioFederado socioFederado = new SocioFederado(nif, federacion, nombre);
        entityManager.getTransaction().begin(); // Inicia la transacción
        try {
            entityManager.persist(federacion); // Persiste el seguro primero
            entityManager.persist(socioFederado); // Luego persiste el socio
            entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback en caso de error
            throw e; // Opcionalmente relanza la excepción para manejo externo
        }
    }

    public void addSocioInfantil(String nombre) {
        SocioInfantil socioInfantil = new SocioInfantil(nombre);

        entityManager.getTransaction().begin(); // Inicia la transacción
        try {
            entityManager.persist(socioInfantil); // Luego persiste el socio
            entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback en caso de error
            throw e; // Opcionalmente relanza la excepción para manejo externo
        }
    }

    public List<Socio> obtenerListaSocios() {
        List<? extends Socio> listaSociosEstandar = obtenerListaSociosEstandar();
        List<? extends Socio> listaSociosFederados = obtenerListaSociosFederados();
        List<Socio> socios = new ArrayList<>();
        for (Socio socio : listaSociosEstandar) {
            socios.add(socio);
        }
        for (Socio socio : listaSociosFederados) {
            socios.add(socio);
        }
        return socios;
    }

    public boolean eliminarSocio(int nsocio) {
        Socio socio = entityManager.find(Socio.class, nsocio);
        if (socio != null) {
            entityManager.getTransaction().begin(); // Inicia la transacción
            try {
                entityManager.remove(socio);
                entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
                return true;
            } catch (Exception e) {
                entityManager.getTransaction().rollback(); // Rollback en caso de error
                throw e; // Opcionalmente relanza la excepción para manejo externo
            }
        }
        return false;
    }

    public List<Socio> obtenerListaSociosEstandar() {
        List<SocioEstandar> sociosE = entityManager.createQuery("SELECT s FROM SocioEstandar s", SocioEstandar.class).getResultList();
        List<Socio> socios = new ArrayList<>();
        for (Socio socio : sociosE) {
            socios.add(socio);
        }
        return socios;
    }

    public List<Socio> obtenerListaSociosFederados() {
        List<SocioFederado> sociosF = entityManager.createQuery("SELECT s FROM SocioFederado s", SocioFederado.class).getResultList();
        List<Socio> socios = new ArrayList<>();
        for (Socio socio : sociosF) {
            socios.add(socio);
        }
        return socios;
    }

    public void modificarSeguroSocio(int nsocio, String nuevoTipoSeguro) {
        SocioEstandar socio = entityManager.find(SocioEstandar.class, nsocio);
        if (socio != null) {
            Seguro seguro = new Seguro(nuevoTipoSeguro);
            entityManager.getTransaction().begin(); // Inicia la transacción
            try {
                entityManager.persist(seguro); // Asegúrate de persistir el nuevo Seguro.
                socio.setSeguro(seguro);
                entityManager.refresh(socio);
                entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
            } catch (Exception e) {
                entityManager.getTransaction().rollback(); // Rollback en caso de error
                throw e; // Opcionalmente relanza la excepción para manejo externo
            }
        }
    }

    public void crearExcursion(String descripcion, Double precio, Date fecha, int dias) {
        Excursion excursion = new Excursion(descripcion, precio, fecha, dias);
        entityManager.getTransaction().begin(); // Inicia la transacción
        try {
            entityManager.persist(excursion); // Asegúrate de persistir el nuevo Seguro.
            entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback en caso de error
            throw e; // Opcionalmente relanza la excepción para manejo externo
        }
    }

    public List<Excursion> filtrarExcursiones(Date fechaInicio, Date fechaFin) {
        return entityManager.createQuery("SELECT e FROM Excursion e WHERE e.fecha BETWEEN :fechaInicio AND :fechaFin", Excursion.class)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();
    }

    public List<Excursion> obtenerListaExcursiones() {
        return entityManager.createQuery("SELECT e FROM Excursion e", Excursion.class).getResultList();
    }

    public List<Inscripcion> obtenerListaInscripciones(String nombre, Date fecha) {
        return entityManager.createQuery("SELECT i FROM Inscripcion i WHERE i.socio.nombre = :nombre AND i.excursion.fecha = :fecha", Inscripcion.class)
                .setParameter("nombre", nombre)
                .setParameter("fecha", fecha)
                .getResultList();
    }

    public void crearInscripcion(int nsocio, int nexcursion) {
        Socio socio = entityManager.find(Socio.class, nsocio);
        Excursion excursion = entityManager.find(Excursion.class, nexcursion);
        if (socio != null && excursion != null) {
            Inscripcion inscripcion = new Inscripcion(socio, excursion);
            entityManager.getTransaction().begin(); // Inicia la transacción
            try {
                entityManager.persist(inscripcion); // Asegúrate de persistir el nuevo Seguro.
                entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
            } catch (Exception e) {
                entityManager.getTransaction().rollback(); // Rollback en caso de error
                throw e; // Opcionalmente relanza la excepción para manejo externo
            }
        }
    }

    public boolean eliminarInscripcion(int ninscripcion) {
        Inscripcion inscripcion = entityManager.find(Inscripcion.class, ninscripcion);
        if (inscripcion != null) {
            entityManager.getTransaction().begin(); // Inicia la transacción
            try {
                entityManager.persist(inscripcion); // Asegúrate de persistir el nuevo Seguro.
                entityManager.getTransaction().commit(); // Commit la transacción si todo está correcto
            } catch (Exception e) {
                entityManager.getTransaction().rollback(); // Rollback en caso de error
                throw e; // Opcionalmente relanza la excepción para manejo externo
            }
            return true;
        }
        return false;
    }
}
