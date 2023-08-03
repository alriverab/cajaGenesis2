package cl.bch.cajagenesis.services;

import cl.bch.cajagenesis.dto.ResponseConsultaCajaDto;
import cl.bch.cajagenesis.dto.ResponseDto;
import cl.bch.cajagenesis.dto.ResponseReglaDto;
import cl.bch.cajagenesis.persistence.entties.Regla;
import cl.bch.cajagenesis.persistence.repository.ReglaDAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio encargada de operar las Reglas
 *
 * @author 160k
 */
@Component
public class ReglaBL {

    @Autowired
    private ReglaDAL regDAL;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReglaBL.class);
    private static final String STACK = "Stack: ";

    /**
     * Obtener lista de reglas
     *
     * @param reg codigo del tipo de cliente
     * @return
     * @throws SQLException
     */
    public ResponseReglaDto listarReglas(Regla reg) {
        ResponseReglaDto res = new ResponseReglaDto();
        List<Regla> listaResp = new ArrayList<>();

        try {

        List<Object[]> listBD = regDAL.listarReglas(reg, reg.getCreadoPor());
        Boolean primerReg = false;

        for (Object[] result : listBD) {
            if (Boolean.TRUE.equals(!primerReg)) {
                res.setStatusCode(Integer.parseInt(result[0].toString()));
                res.setMessage(result[1].toString());
                primerReg = true;
            }

            Regla regl = new Regla();
            regl.setIdRegla(getValorInteger(result, 2));
            regl.setTipoDeCliente(getValorString(result, 3));
            regl.setSegmento(getValorString(result, 4));
            regl.setTipoDeCuenta(getValorString(result, 5));
            regl.setLimiteAbonos(getValorString(result, 6));
            regl.setMoneda(getValorString(result, 7));
            regl.setTransaccion(getValorString(result, 8));
            regl.setMiscelaneo(getValorString(result, 9));
            regl.setCodOficina(getValorString(result, 10));
            regl.setOficina(getValorString(result, 11));
            regl.setEstado(getValorString(result, 12));
            regl.setCreadoPor(getValorString(result, 13));
            regl.setNombreTipoCuenta(getValorString(result, 14));
            regl.setCodMoneda(getValorString(result, 15));
            listaResp.add(regl);
        }
        res.setReglas(listaResp);
        } catch (Exception ex) {

            LOGGER.error("Error al listar reglas: {}", ex.getMessage());
            LOGGER.error(STACK, ex);
        }

        return res;
    }

    private static String getValorString(Object[] result, int indice) {
        return result[indice] == null ? "" : result[indice].toString();
    }

    private static int getValorInteger(Object[] result, int indice) {
        return result[indice] == null ? 0 : Integer.parseInt(result[indice].toString());
    }

    /**
     * Obtener lista de consulta caja
     *
     * @param reg Regla
     * @return
     * @throws SQLException
     */
    public List<ResponseConsultaCajaDto> listarConsultaCaja(Regla reg) {
        List<ResponseConsultaCajaDto> res = new ArrayList<>();
        List<Object[]> listBD = regDAL.listarReglas(reg, reg.getCreadoPor());

        for (Object[] result : listBD) {
            ResponseConsultaCajaDto regl = new ResponseConsultaCajaDto();
            regl.setStatusCode(result[0].toString());
            regl.setMessage(result[1].toString());
            regl.setLimiteAbonos(Integer.parseInt(result[3].toString()));
            res.add(regl);
        }
        return res;
    }

    /**
     * Eliminar regla
     *
     * @param IdRegla   id de la regla a eliminar
     * @param CreadoPor creado por para registro
     * @return obj response de eliminación
     * @throws SQLException Excepción SQL
     */
    public ResponseDto deleteRegla(int idRegla, String creadoPor) throws SQLException {
        ResponseDto res = new ResponseDto();
        List<Object[]> listBD = regDAL.deleteRegla(idRegla, creadoPor);

        for (Object[] result : listBD) {
            ResponseDto regl = new ResponseDto();
            regl.setStatusCode(result[0].toString());
            regl.setMessage(result[1].toString());
        }
        return res;
    }

    /**
     * Insertar Regla
     *
     * @param reg Regla
     * @return
     * @throws SQLException
     */
    public ResponseDto insertarRegla(Regla reg) {
        ResponseDto res = new ResponseDto();
        List<Object[]> listBD = regDAL.insertRegla(reg, reg.getCreadoPor());

        for (Object[] result : listBD) {
            ResponseDto regl = new ResponseDto();
            regl.setStatusCode(result[0].toString());
            regl.setMessage(result[1].toString());
        }
        return res;
    }

    /**
     * Desactivar Regla
     *
     * @param IdRegla   id de la regla
     * @param Estado    estado de la regla
     * @param CreadoPor creado por de la regla
     * @return obj response
     * @throws SQLException excepción SQL
     */
    public ResponseDto desactivarRegla(
            int idRegla, String estado, String creadoPor) throws SQLException {
        ResponseDto res = new ResponseDto();
        List<Object[]> listBD = regDAL.desactivarRegla(idRegla, estado, creadoPor);

        for (Object[] result : listBD) {
            ResponseDto regl = new ResponseDto();
            regl.setStatusCode(result[0].toString());
            regl.setMessage(result[1].toString());
        }
        return res;
    }

    /**
     * Actualizar Regla
     *
     * @param reg Regla
     * @return
     * @throws SQLException
     */
    public ResponseDto actualizarRegla(Regla reg) {
        ResponseDto res = new ResponseDto();
        List<Object[]> listBD = regDAL.updateRegla(reg, reg.getCreadoPor());

        for (Object[] result : listBD) {
            ResponseDto regl = new ResponseDto();
            regl.setStatusCode(result[0].toString());
            regl.setMessage(result[1].toString());
        }
        return res;
    }
}