package cl.bch.cajagenesis.persistence.repository;

import cl.bch.cajagenesis.persistence.entties.Transaccion;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa DAL para la Transaccion
 *
 * @author 160k
 */
@Repository
@PropertySource({"file:${APPS_PROPS}/Config/CAJAGEN-properties.properties"})
public class TransaccionDAL {

    @PersistenceContext
    private EntityManager em;

    /**
     * Listar tipos de cliente
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> listarTransacciones() {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_transaccion.sp_listarTransacciones")
                    .registerStoredProcedureParameter(1, Transaccion.class, ParameterMode.REF_CURSOR);

            query.execute();

            lista = query.getResultList();
        } catch (Exception ex) {
             }
        return lista;
    }
}