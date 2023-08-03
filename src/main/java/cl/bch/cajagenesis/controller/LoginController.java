package cl.bch.cajagenesis.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Controlador de Reglas
 *
 * @author 160k
 */
@RestController
public class LoginController {

    /**
     * registro log login o logaount
     *
     * @return string de respuesta de login (OK, ERROR)
     */
    @ResponseBody
    @GetMapping(value = "/log-sesion")
    public String logSesion() {
        return "OK";
    }
}
