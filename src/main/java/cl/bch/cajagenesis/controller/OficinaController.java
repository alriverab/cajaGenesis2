package cl.bch.cajagenesis.controller;

import cl.bch.cajagenesis.persistence.entties.Oficina;
import cl.bch.cajagenesis.services.OficinaBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static cl.bch.cajagenesis.enums.Roles.*;

/**
 * Controlador de Oficinas
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class OficinaController {
    @Autowired
    private OficinaBL ofBL;

    /**
     * Listar oficinas
     *
     * @return lista de oficinas
     */
    @ResponseBody
    @GetMapping(value = "/listar-Oficinas")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<Oficina> listarOficinas() {
        return ofBL.listarOficinas();
    }
}
