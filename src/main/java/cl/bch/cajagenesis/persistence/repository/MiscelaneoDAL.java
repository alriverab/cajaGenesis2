package cl.bch.cajagenesis.persistence.repository;

import cl.bch.cajagenesis.persistence.entties.TipoCliente;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa DAL para el Miscelaneo
 *
 * @author 160k
 */
@Repository
public class MiscelaneoDAL {

    @PersistenceContext
    private EntityManager em;

    /**
     * Listar Miscelaneos
     *
     * @return lista de objetos
     * @throws SQLException
     */
    public List<Object[]> listarMiscelaneos(int idMoneda) {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_miscelaneo.sp_listarMiscelaneo")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .setParameter(1, idMoneda)
                    .registerStoredProcedureParameter(2, TipoCliente.class, ParameterMode.REF_CURSOR);
            query.execute();

            @SuppressWarnings("unchecked")
            List<Object[]> listResp = query.getResultList();

            return listResp;
        } catch (Exception ex) {

        }
        return lista;
    }
}