package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.persistence.entties.Miscelaneo;

import cl.bch.cajagenesis.persistence.repository.MiscelaneoDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar Miscelaneo
 *
 * @author 160k
 */
@Component
public class MiscelaneoBL {

    @Autowired
    private MiscelaneoDAL misDAL;

    /**
     * Obtener lista de Miscelaneo
     *
     * @return
     * @throws SQLException
     */
    public List<Miscelaneo> listarMiscelaneos(int idMoneda) {
        List<Miscelaneo> listaResp = new ArrayList<>();
        List<Object[]> listBD = misDAL.listarMiscelaneos(idMoneda);

        for (Object[] result : listBD) {
            Miscelaneo mis = new Miscelaneo();
            mis.setGlosaCodigoVario(result[0] == null ? "" : result[0].toString());
            mis.setGlosaExtendido(result[1] == null ? "" : result[1].toString());
            mis.setCodigoExtendido(result[2] == null ? 0 : Integer.parseInt(result[2].toString()));
            mis.setModoPago(result[3] == null ? "" : result[3].toString());
            mis.setMoneda(result[4] == null ? "" : result[4].toString());
            mis.setCodigoTransaccion(result[5] == null ? 0 : Integer.parseInt(result[5].toString()));
            listaResp.add(mis);
        }
        return listaResp;
    }
}