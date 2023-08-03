package cl.bch.cajagenesis.persistence.repository;

import cl.bch.cajagenesis.dto.ResponseDto;
import cl.bch.cajagenesis.persistence.entties.Regla;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa DAL para Reglas
 *
 * @author 160k
 */
@Repository
@PropertySource({"file:${APPS_PROPS}/Config/CAJAGEN-properties.properties"})
public class ReglaDAL {
    private static final Logger
            LOGGER = LogManager.getLogger(ReglaDAL.class);

    private static final String STACK = "Stack: ";

    @PersistenceContext
    private EntityManager em;

    /**
     * Listar reglas
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> listarReglas(Regla reg, String creadoPor) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_reglas.sp_listarReglas")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .setParameter(1, reg.getMoneda() != null ? reg.getMoneda() : "")
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .setParameter(2, reg.getTransaccion() != null ? reg.getMoneda() : "")
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .setParameter(3, reg.getMiscelaneo() != null ? reg.getMoneda() : "")
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(4, reg.getExtendido() != null ? reg.getMoneda() : "")
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .setParameter(5, reg.getOficina() != null ? reg.getMoneda() : "")
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .setParameter(6, reg.getEstado() != null ? reg.getMoneda() : "")
                    .registerStoredProcedureParameter(7, Regla.class, ParameterMode.REF_CURSOR);

            query.execute();
            LOGGER.info("ok en reglas");
            lista = query.getResultList();
        } catch (Exception ex) {

        }

        return lista;
    }

    /**
     * Listar Consulta Caja
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> consultaCaja(Regla reg) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_reglas.sp_consultaCaja")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .setParameter(1, reg.getTipoDeCliente())
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .setParameter(2, reg.getSegmento())
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .setParameter(3, reg.getTipoDeCuenta())
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(4, reg.getMoneda())
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .setParameter(5, reg.getTransaccion())
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .setParameter(6, reg.getMiscelaneo())
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(7, reg.getExtendido())
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .setParameter(8, reg.getOficina())
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .setParameter(9, reg.getEstado())
                    .registerStoredProcedureParameter(10, Regla.class, ParameterMode.REF_CURSOR);

            query.execute();

            lista = query.getResultList();
        } catch (Exception ex) {
        }

        return lista;
    }

    /**
     * Actualizar Regla
     *
     * @return lista que contiene el obj regla actualizada
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> updateRegla(Regla reg, String creadoPor) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_reglas.sp_reglaUpdate")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .setParameter(1, reg.getIdRegla())
                    .registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN)
                    .setParameter(2, Integer.parseInt(reg.getTipoDeCliente()))
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .setParameter(3, reg.getSegmento())
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(4, reg.getTipoDeCuenta())
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .setParameter(5, reg.getLimiteAbonos())
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .setParameter(6, reg.getOficina())
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(7, reg.getEstado())
                    .registerStoredProcedureParameter(8, ResponseDto.class, ParameterMode.REF_CURSOR);

            query.execute();


            lista = query.getResultList();
        } catch (Exception ex) {
        }
        return lista;
    }

    /**
     * Desactivar Regla
     *
     * @return lista que contiene el objeto regla activada/desactivada
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> desactivarRegla(int idRegla, String estado, String creadoPor) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_reglas.sp_DesactivarRegla")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .setParameter(1, idRegla)
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .setParameter(2, estado)
                    .registerStoredProcedureParameter(3, ResponseDto.class, ParameterMode.REF_CURSOR);

            query.execute();
            lista = query.getResultList();
        } catch (Exception ex) {

        }
        return lista;
    }

    /**
     * Insertar Regla
     *
     * @return lista que contiene el objeto de la regla insertada
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> insertRegla(Regla reg, String creadoPor) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_reglas.sp_reglaInsert")
                    .registerStoredProcedureParameter(1, String.class, ParameterMode.IN)
                    .setParameter(1, reg.getTipoDeCliente())
                    .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
                    .setParameter(2, reg.getSegmento())
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .setParameter(3, reg.getTipoDeCuenta())
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .setParameter(4, reg.getLimiteAbonos())
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .setParameter(5, reg.getMoneda())
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .setParameter(6, reg.getTransaccion())
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .setParameter(7, reg.getMiscelaneo())
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .setParameter(8, reg.getExtendido())
                    .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                    .setParameter(9, reg.getOficina())
                    .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                    .setParameter(10, reg.getEstado())
                    .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                    .setParameter(11, reg.getCreadoPor())
                    .registerStoredProcedureParameter(12, ResponseDto.class, ParameterMode.REF_CURSOR);

            query.execute();


            lista = query.getResultList();
        } catch (Exception ex) {
        }

        return lista;
    }

    /**
     * Insertar Regla
     *
     * @return lista que contiene el objeto de la regla eliminada
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> deleteRegla(int idRegla, String creadoPor) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_reglas.sp_reglaDelete")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .setParameter(1, idRegla)
                    .registerStoredProcedureParameter(2, ResponseDto.class, ParameterMode.REF_CURSOR);
            query.execute();


            lista = query.getResultList();
        } catch (Exception ex) {
        }
        return lista;
    }

}