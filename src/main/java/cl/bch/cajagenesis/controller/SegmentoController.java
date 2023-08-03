package cl.bch.cajagenesis.controller;

import cl.bch.cajagenesis.persistence.entties.Segmento;
import cl.bch.cajagenesis.services.SegmentoBL;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

import static cl.bch.cajagenesis.enums.Roles.*;

/**
 * Controlador de Segmento
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class SegmentoController {

    @Autowired
    private SegmentoBL segBl;

    /**
     * Listar segmentos por tipo de cliente
     *
     * @return lista de segmentos por tipo de cliente
     */
    @ResponseBody
    @GetMapping(value = "/listar-segmentos-tipo-cliente")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<Segmento> listarSegmentoTipoCliente(@RequestParam("codigoTipoCliente") int codigoTipoCliente) {
        return segBl.listarSegmentoTipoCliente(codigoTipoCliente);
    }
}
