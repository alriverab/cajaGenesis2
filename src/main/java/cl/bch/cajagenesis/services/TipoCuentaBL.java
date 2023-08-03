package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.persistence.entties.TipoCuenta;
import cl.bch.cajagenesis.persistence.repository.TipoCuentaDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar Tipos de Cuentas
 *
 * @author 160k
 */
@Component
public class TipoCuentaBL {

    @Autowired
    private TipoCuentaDAL tipoCuentaDAL;

    /**
     * Obtener lista de segmentos por tipo de cuentas
     *
     * @return
     * @throws SQLException
     */
    public List<TipoCuenta> listarTiposCuenta() {
        List<TipoCuenta> listaResp = new ArrayList<>();
        List<Object[]> listBD = tipoCuentaDAL.listarTipoCuentas();

        for (Object[] result : listBD) {
            TipoCuenta tipoCuenta = new TipoCuenta();
            tipoCuenta.setGlosa(result[0].toString());
            tipoCuenta.setToken(result[1].toString());
            listaResp.add(tipoCuenta);
        }
        return listaResp;
    }
}