package cl.bch.cajagenesis.persistence.entties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Transaccion
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "transaccion")
public class Transaccion {
    @Id
    @Column(name = "codigotransaccion")
    private int codigoTransaccion;

    @Column(name = "glosatransaccion")
    private String glosaTransaccion;
}
