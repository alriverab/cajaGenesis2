package cl.bch.cajagenesis.controller;

import cl.bch.cajagenesis.persistence.entties.Miscelaneo;
import cl.bch.cajagenesis.services.MiscelaneoBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static cl.bch.cajagenesis.enums.Roles.*;

/**
 * Controlador de Tipo Cliente
 *
 * @author 160k
 */
@RestController
@RequestMapping("/v1.0/service")
public class MiscelaneoController {

    @Autowired
    private MiscelaneoBL misBL;

    /**
     * Listar Miscelaneo
     *
     * @return lista de miscelaneos
     */
    @GetMapping(value = "/listar-miscelaneo")
    @RolesAllowed({ APP_EJECUTIVO, APP_SUPERVISOR, APP_ADMINISTRADOR })
    public List<Miscelaneo> listarMiscelaneo(int idMoneda) {
        return misBL.listarMiscelaneos(idMoneda);
    }
}
