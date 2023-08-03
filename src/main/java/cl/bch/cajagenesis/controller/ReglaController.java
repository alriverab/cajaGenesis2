package cl.bch.cajagenesis.controller;


import cl.bch.cajagenesis.dto.ResponseConsultaCajaDto;
import cl.bch.cajagenesis.dto.ResponseDto;
import cl.bch.cajagenesis.dto.ResponseReglaDto;
import cl.bch.cajagenesis.persistence.entties.Regla;
import cl.bch.cajagenesis.services.ReglaBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static cl.bch.cajagenesis.enums.Roles.*;


/**
 * Controlador de Reglas
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class
ReglaController {

    @Autowired
    private ReglaBL rgBL;

    /**
     * Listar Consulta Caja
     *
     * @param regla reglas
     * @return lista de consultas de caja
     */

    @GetMapping(value = "/listar-consulta-caja")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public List<ResponseConsultaCajaDto> listarConsultaCja(@RequestBody Regla regla) {
        Regla regla2 = new Regla();
        regla2.setIdRegla(regla.getIdRegla());
        regla2.setTipoDeCliente(regla.getTipoDeCliente());
        regla2.setSegmento(regla.getSegmento());
        regla2.setTipoDeCuenta(regla.getTipoDeCuenta());
        regla2.setMoneda(regla.getMoneda());
        regla2.setTransaccion(regla.getTransaccion());
        regla2.setMiscelaneo(regla.getMiscelaneo());
        regla2.setExtendido(regla.getExtendido());
        regla2.setOficina(regla.getOficina());
        regla2.setCodOficina(regla.getCodOficina());
        regla2.setEstado(regla.getEstado());
        regla2.setLimiteAbonos(regla.getLimiteAbonos());
        regla2.setCreadoPor(regla.getCreadoPor());
        regla2.setNombreTipoCuenta(regla.getNombreTipoCuenta());
        regla2.setCodMoneda(regla.getCodMoneda());

        List<ResponseConsultaCajaDto> lista = new ArrayList<>();
        try {
            lista = rgBL.listarConsultaCaja(regla2);
        } catch (Exception ex) {
        }
        return lista;
    }


    /**
     * Listar reglas
     *
     * @return lista de reglas
     */
    @GetMapping(value = "/listar-reglas")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public ResponseReglaDto listarReglas() {
        Regla regla = new Regla();
        return rgBL.listarReglas(regla);
    }



    /**
     * @param regla reglas
     * @return obj de regla insertada
     */
    @PostMapping(value = "/insertar-regla", consumes = "application/json")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public ResponseDto insertarRegla(@RequestBody Regla regla) {
        Regla regla2 = new Regla();
        regla2.setIdRegla(regla.getIdRegla());
        regla2.setTipoDeCliente(regla.getTipoDeCliente());
        regla2.setSegmento(regla.getSegmento());
        regla2.setTipoDeCuenta(regla.getTipoDeCuenta());
        regla2.setMoneda(regla.getMoneda());
        regla2.setTransaccion(regla.getTransaccion());
        regla2.setMiscelaneo(regla.getMiscelaneo());
        regla2.setExtendido(regla.getExtendido());
        regla2.setOficina(regla.getOficina());
        regla2.setCodOficina(regla.getCodOficina());
        regla2.setEstado(regla.getEstado());
        regla2.setLimiteAbonos(regla.getLimiteAbonos());
        regla2.setCreadoPor(regla.getCreadoPor());
        regla2.setNombreTipoCuenta(regla.getNombreTipoCuenta());
        regla2.setCodMoneda(regla.getCodMoneda());
        ResponseDto lista = new ResponseDto();
        try {
            lista = rgBL.insertarRegla(regla2);
        } catch (Exception ex) {

        }
        return lista;
    }

    /**
     * @param regla reglas
     * @return obj de regla actualizada
     */
    @PutMapping(value = "/update-regla")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public ResponseDto actualizarRegla(@RequestBody Regla regla) {
        Regla regla2 = new Regla();
        regla2.setIdRegla(regla.getIdRegla());
        regla2.setTipoDeCliente(regla.getTipoDeCliente());
        regla2.setSegmento(regla.getSegmento());
        regla2.setTipoDeCuenta(regla.getTipoDeCuenta());
        regla2.setMoneda(regla.getMoneda());
        regla2.setTransaccion(regla.getTransaccion());
        regla2.setMiscelaneo(regla.getMiscelaneo());
        regla2.setExtendido(regla.getExtendido());
        regla2.setOficina(regla.getOficina());
        regla2.setCodOficina(regla.getCodOficina());
        regla2.setEstado(regla.getEstado());
        regla2.setLimiteAbonos(regla.getLimiteAbonos());
        regla2.setCreadoPor(regla.getCreadoPor());
        regla2.setNombreTipoCuenta(regla.getNombreTipoCuenta());
        regla2.setCodMoneda(regla.getCodMoneda());

        ResponseDto lista = new ResponseDto();
        try {
            lista = rgBL.actualizarRegla(regla2);
        } catch (Exception ex) {
        }
        return lista;
    }

    @PutMapping(value = "/actualizar-regla")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public ResponseEntity<?> updateRegla(@RequestBody Regla regla) {
        try {
            ResponseDto response = rgBL.actualizarRegla(regla);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            // Puedes agregar un logger aquí para registrar el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Un error ocurrido al modificar reglas.");
        }
    }


    @PutMapping(value = "/desactivar-regla")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public ResponseDto desactivarRegla(int idRegla, String estado, String creadoPor) {
        ResponseDto lista = new ResponseDto();
        try {
            lista = rgBL.desactivarRegla(idRegla, estado, creadoPor);
        } catch (Exception ex) {        }
        return lista;
    }


    @DeleteMapping(value = "/eliminar-regla")
    @RolesAllowed({APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR})
    public ResponseDto eliminarRegla(int idRegla, String creadoPor) throws SQLException {
        return rgBL.deleteRegla(idRegla, creadoPor);
    }
}
