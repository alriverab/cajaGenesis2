package cl.bch.cajagenesis.persistence.repository;


import cl.bch.cajagenesis.persistence.entties.TipoCuenta;
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
 * Capa DAL para el TIPO CLIENTE
 *
 * @author 160k
 */
@Repository
@PropertySource({"file:${APPS_PROPS}/Config/CAJAGEN-properties.properties"})
public class TipoCuentaDAL {

    @PersistenceContext
    private EntityManager em;

    /**
     * Listar tipos de cuenta
     *
     * @return lista de objetos
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> listarTipoCuentas() {
        List<Object[]> lista = new ArrayList<>();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("pkg_tipocuenta.sp_listarTipoCuenta")
                    .registerStoredProcedureParameter(1, TipoCuenta.class, ParameterMode.REF_CURSOR);

            query.execute();

            lista = query.getResultList();
        } catch (Exception ex) {
                  }
        return lista;
    }
}