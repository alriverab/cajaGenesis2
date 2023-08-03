package cl.bch.cajagenesis.controller;

import cl.bch.cajagenesis.persistence.entties.Moneda;
import cl.bch.cajagenesis.services.MonedaBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static cl.bch.cajagenesis.enums.Roles.*;

/**
 * Controlador de Moneda
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class MonedaController {
    @Autowired
    private MonedaBL moBL;

    /**
     * Listar Monedas
     *
     * @return lista de monedas
     */
    @ResponseBody
    @GetMapping(value = "/listar-monedas")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<Moneda> listarMonedas() {
        return moBL.listarMonedas();
    }



}
