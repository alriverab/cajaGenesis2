package cl.bch.cajagenesis.controller;


import cl.bch.cajagenesis.persistence.entties.TipoCuenta;
import cl.bch.cajagenesis.services.TipoCuentaBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static cl.bch.cajagenesis.enums.Roles.*;


/**
 * Controlador de Tipo Cuenta
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class TipoCuentaController {
    @Autowired
    private TipoCuentaBL tpBL;

    /**
     * Listar tipos de cuentas
     *
     * @return lista de tipos de cuentas
     */
    @ResponseBody
    @GetMapping(value = "/listar-tipos-cuentas")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<TipoCuenta> listarTiposCuentas() {
        return tpBL.listarTiposCuenta();
    }
}
