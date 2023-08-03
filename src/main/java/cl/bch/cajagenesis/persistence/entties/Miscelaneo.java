package cl.bch.cajagenesis.persistence.entties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Miscelaneo
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "miscelaneo")
public class Miscelaneo {

    @Column(name = "codigo_extendido")
    private int codigoExtendido;

    @Column(name = "codigo_transaccion")
    private int codigoTransaccion;

    @Column(name = "glosa_codigo_vario")
    private String glosaCodigoVario;

    @Id
    @Column(name = "glosa_extenido")
    private String glosaExtendido;

    @Column(name = "modo_pago")
    private String modoPago;

    @Column(name = "moneda")
    private String moneda;

}
