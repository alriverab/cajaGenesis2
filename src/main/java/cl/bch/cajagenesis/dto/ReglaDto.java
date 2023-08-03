package cl.bch.cajagenesis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReglaDto {
    private int idRegla;
    private String tipoDeCliente;
    private String segmento;
    private String tipoDeCuenta;
    private String moneda;
    private String transaccion;
    private String miscelaneo;
    private String extendido;
    private String oficina;
    private String codoficina;
    private String estado;
    private String limiteAbonos;
    private String creadoPor;
    private String nombreTipoCuenta;
    private String codMoneda;
}
