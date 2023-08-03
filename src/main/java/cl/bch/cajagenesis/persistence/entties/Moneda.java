package cl.bch.cajagenesis.persistence.entties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Moneda
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "moneda")
public class Moneda {

    @Id
    @Column(name = "codmoneda")
    private int codMoneda;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cambiocompra")
    private int cambioCompra;

    @Column(name = "cambioventa")
    private int cambioVenta;
}
