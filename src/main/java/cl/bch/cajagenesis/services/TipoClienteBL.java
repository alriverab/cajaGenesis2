package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.persistence.entties.TipoCliente;
import cl.bch.cajagenesis.persistence.repository.TipoClienteDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar los tipos de cliente
 *
 * @author 160k
 */
@Component
public class TipoClienteBL {

    @Autowired
    private TipoClienteDAL tpCliDAL;

    /**
     * Obtener lista de tipos de clientes
     *
     * @return
     * @throws SQLException
     */
    public List<TipoCliente> listarTiposCliente() {
        List<TipoCliente> listaResp = new ArrayList<>();
        List<Object[]> listBD = tpCliDAL.listarTiposCliente();

        for (Object[] result : listBD) {
            TipoCliente tp = new TipoCliente();
            tp.setGlosa(result[0].toString());
            tp.setCodigo(Integer.parseInt(result[1].toString()));
            listaResp.add(tp);
        }
        return listaResp;
    }
}