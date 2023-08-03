package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.persistence.entties.Segmento;
import cl.bch.cajagenesis.persistence.repository.SegmentoDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar los Segmentos
 *
 * @author 160k
 */
@Component
public class SegmentoBL {

    @Autowired
    private SegmentoDAL segDAL;

    /**
     * Obtener lista de segmentos por tipo de cliente
     *
     * @param codigoTipoCliente codigo del tipo de cliente
     * @return
     * @throws SQLException
     */
    public List<Segmento> listarSegmentoTipoCliente(int codigoTipoCliente) {
        List<Segmento> listaResp = new ArrayList<>();
        List<Object[]> listBD = segDAL.listarSegmentosTipoCliente(codigoTipoCliente);

        for (Object[] result : listBD) {
            Segmento seg = new Segmento();
            seg.setGlosa(result[0].toString());
            seg.setCodigoSegmento(Integer.parseInt(result[1].toString()));
            seg.setCodigoTipoCliente(codigoTipoCliente);
            listaResp.add(seg);
        }
        return listaResp;
    }
}