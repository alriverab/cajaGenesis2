package cl.bch.cajagenesis.controller;


import cl.bch.cajagenesis.persistence.entties.Transaccion;
import cl.bch.cajagenesis.services.TransaccionBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static cl.bch.cajagenesis.enums.Roles.*;


/**
 * Controlador de Transacciones
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class TransaccionController {

    @Autowired
    private TransaccionBL trBL;

    /**
     * Listar Transacciones
     *
     * @return lista de tipos de transacciones
     */
    @ResponseBody
    @GetMapping(value = "/listar-transacciones")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<Transaccion> listarTransacciones() {
        return trBL.listarTransacciones();
    }
}
