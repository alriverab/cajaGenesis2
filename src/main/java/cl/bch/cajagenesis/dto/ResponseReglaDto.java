package cl.bch.cajagenesis.dto;

import cl.bch.cajagenesis.persistence.entties.Regla;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO para traer response reglaDtos
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReglaDto {
    private int statusCode;
    private String message;
    List<Regla> reglas;
}
