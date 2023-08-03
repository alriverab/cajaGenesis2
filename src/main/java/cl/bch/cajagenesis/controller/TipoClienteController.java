package cl.bch.cajagenesis.controller;

import cl.bch.cajagenesis.persistence.entties.TipoCliente;
import cl.bch.cajagenesis.services.TipoClienteBL;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

import static cl.bch.cajagenesis.enums.Roles.*;

/**
 * Controlador de Tipo Cliente
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class TipoClienteController {
    @Autowired
    private TipoClienteBL tpBL;

    /**
     * Listar tipos de cliente
     *
     * @return lista de tipos de clientes
     */
    @ResponseBody
    @GetMapping(value = "/listar-tipos-cliente")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<TipoCliente> listarTiposCliente() {
        return tpBL.listarTiposCliente();
    }
}
