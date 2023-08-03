package cl.bch.cajagenesis.persistence.entties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Oficina
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "oficina_parametro")
public class Oficina {

    @Id
    @Column(name = "codoficina")
    private int codoficina;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "crp")
    private String crp;

    @Column(name = "migrada")
    private String migrada;

    @Column(name = "genera_contabilidad")
    private String generaContabilidad;

    @Column(name = "genera_pil")
    private String generaPil;

    @Column(name = "cui_virtual")
    private String cuiVirtual;

    @Column(name = "cod_canal")
    private int codCanal;

    @Column(name = "cod_nodo")
    private int codNodo;

    @Column(name = "socia")
    private String socia;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "val_bch")
    private String valBch;

    @Column(name = "val_cosmo")
    private String valCosmo;

}
