package cl.bch.cajagenesis.persistence.repository;

import cl.bch.cajagenesis.persistence.entties.TipoCliente;

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
 * Capa DAL para el Oficina
 *
 * @author 160k
 */
@Repository
@PropertySource({"file:${APPS_PROPS}/Config/CAJAGEN-properties.properties"})
public class OficinaDAL {

    @PersistenceContext
    private EntityManager em;

    /**
     * Listar Oficinas
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> listarOficinas() {
        List<Object[]> lista = new ArrayList<>();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery(
                            "pkg_oficinaParametros.sp_listarOficinasParametros")
                    .registerStoredProcedureParameter(1, TipoCliente.class, ParameterMode.REF_CURSOR);
            query.execute();
            lista = query.getResultList();
        } catch (Exception ex) {

        }
        return lista;
    }

}