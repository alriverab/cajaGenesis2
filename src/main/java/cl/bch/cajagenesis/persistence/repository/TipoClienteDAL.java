package cl.bch.cajagenesis.persistence.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import cl.bch.cajagenesis.persistence.entties.TipoCliente;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 * Capa DAL para el TIPO CLIENTE
 *
 * @author 160k
 */
@Repository
@PropertySource({"file:${APPS_PROPS}/Config/CAJAGEN-properties.properties"})
public class TipoClienteDAL {
    @PersistenceContext
    private EntityManager em;

    /**
     * Listar tipos de cliente
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> listarTiposCliente() {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery(
                            "pkg_TipoCliente.sp_listarTiposCliente")
                    .registerStoredProcedureParameter(1, TipoCliente.class, ParameterMode.REF_CURSOR);
            query.execute();
            lista = query.getResultList();
        } catch (Exception ex) {
                 }
        return lista;
    }
}