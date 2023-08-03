package cl.bch.cajagenesis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para traer estructura consulta  caja
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseConsultaCajaDto {
    private String statusCode;
    private String message;
    private int limiteAbonos;

}
