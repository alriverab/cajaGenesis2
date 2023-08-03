package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.persistence.entties.Moneda;
import cl.bch.cajagenesis.persistence.repository.MonedaDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar tipos monedas
 *
 * @author 160k
 */
@Component
public class MonedaBL {

    @Autowired
    private MonedaDAL monedaDAL;

    /**
     * Obtener lista de monedas
     *
     * @return
     * @throws SQLException
     */
    public List<Moneda> listarMonedas() {
        List<Moneda> listaResp = new ArrayList<>();
        List<Object[]> listBD = monedaDAL.listarMonedas();

        for (Object[] result : listBD) {
            Moneda mon = new Moneda();
            mon.setCodMoneda(Integer.parseInt(result[0].toString()));
            mon.setNombre(result[1].toString());
            mon.setDescripcion(result[2].toString());
            mon.setCambioCompra(Integer.parseInt(result[3].toString()));
            mon.setCambioVenta(Integer.parseInt(result[4].toString()));
            listaResp.add(mon);
        }
        return listaResp;
    }
}