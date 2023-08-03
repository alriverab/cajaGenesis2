package cl.bch.cajagenesis.persistence.entties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad TipoCliente
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "tipocliente")
public class TipoCliente {

    @Id
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "glosa")
    private String glosa;

}
