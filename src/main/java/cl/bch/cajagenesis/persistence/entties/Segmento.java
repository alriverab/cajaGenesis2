package cl.bch.cajagenesis.persistence.entties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad Segmento
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "segmento")
public class Segmento {

    @Id
    @Column(name = "codigosegmento")
    private int codigoSegmento;

    @Column(name = "codigotipocliente")
    private int codigoTipoCliente;

    @Column(name = "glosa")
    private String glosa;
}
