package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.persistence.entties.Oficina;
import cl.bch.cajagenesis.persistence.repository.OficinaDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar las Oficinas
 *
 * @author 160k
 */
@Component
public class OficinaBL {

    @Autowired
    private OficinaDAL ofDAL;

    /**
     * Obtener lista de oficina
     *
     * @return
     * @throws SQLException
     */
    public List<Oficina> listarOficinas() {
        List<Oficina> listaResp = new ArrayList<>();
        List<Object[]> listBD = ofDAL.listarOficinas();

        transformaObjetoALista(listaResp, listBD);

        return listaResp;
    }

    private static void transformaObjetoALista(List<Oficina> listaResp, List<Object[]> listBD) {
        for (Object[] result : listBD) {
            Oficina of = new Oficina();
            of.setCodoficina(getValorCodigo(result, 0));
            of.setNombre(getValorString(result, 1));
            of.setDescripcion(getValorString(result, 2));
            of.setCrp(getValorString(result, 3));
            of.setMigrada(getValorString(result, 4));
            of.setGeneraContabilidad(getValorString(result, 5));
            of.setGeneraPil(getValorString(result, 6));
            of.setCuiVirtual(getValorString(result, 7));
            of.setCodCanal(getValorCodigo(result, 8));
            of.setCodNodo(getValorCodigo(result, 9));
            of.setSocia(getValorString(result, 10));
            of.setLocalidad(getValorString(result, 11));
            of.setValBch(getValorString(result, 12));
            of.setValCosmo(getValorString(result, 13));
            listaResp.add(of);
        }
    }

    private static String getValorString(Object[] result, int indice) {
        return result[indice] == null ? "" : result[indice].toString();
    }

    private static int getValorCodigo(Object[] result, int indice) {
        return result[indice] == null ? 0 : Integer.parseInt(result[indice].toString());
    }
}