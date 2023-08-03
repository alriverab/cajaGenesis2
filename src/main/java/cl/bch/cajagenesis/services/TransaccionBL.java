package cl.bch.cajagenesis.services;


import cl.bch.cajagenesis.persistence.entties.Transaccion;
import cl.bch.cajagenesis.persistence.repository.TransaccionDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar las Transacciones
 *
 * @author 160k
 */
@Component
public class TransaccionBL {

    @Autowired
    private TransaccionDAL tranDAL;

    /**
     * Obtener lista Transacciones
     *
     * @return
     * @throws SQLException
     */
    public List<Transaccion> listarTransacciones() {
        List<Transaccion> listaResp = new ArrayList<>();
        List<Object[]> listBD = tranDAL.listarTransacciones();
        for (Object[] result : listBD) {
            Transaccion tran = new Transaccion();
            tran.setCodigoTransaccion(Integer.parseInt(result[1].toString()));
            tran.setGlosaTransaccion(result[0].toString());
            listaResp.add(tran);
        }
        return listaResp;
    }
}