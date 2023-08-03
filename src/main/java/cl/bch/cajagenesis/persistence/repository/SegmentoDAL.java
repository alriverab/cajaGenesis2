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
public class SegmentoDAL {

    @PersistenceContext
    private EntityManager em;

    /**
     * Listar Segmentos por tipo Cliente
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> listarSegmentosTipoCliente(int codigoTipoCliente) {
        List<Object[]> lista = new ArrayList<>();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery(
                            "pkg_segmento.sp_listarSegmentoTiposCliente")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, TipoCliente.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, codigoTipoCliente);
            query.execute();

            lista = query.getResultList();
        } catch (Exception ex) {
        }
        return lista;
    }
}