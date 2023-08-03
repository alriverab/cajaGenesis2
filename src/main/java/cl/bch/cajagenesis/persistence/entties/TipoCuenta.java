package cl.bch.cajagenesis.persistence.entties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad TipoCuenta
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "tipocuenta")
public class TipoCuenta {
    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "glosa")
    private String glosa;
}
