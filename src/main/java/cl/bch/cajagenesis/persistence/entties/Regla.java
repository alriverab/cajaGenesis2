package cl.bch.cajagenesis.persistence.entties;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad Regla
 *
 * @author 160k
 */
@Entity
@Setter
@Getter
@Table(name = "reglas")
public class Regla {

    @Id
    @Column(name = "id_regla")
    private int idRegla;

    @Column(name = "tipo_de_cliente")
    public String tipoDeCliente;

    @Column(name = "segmento")
    private String segmento;

    @Column(name = "tipo_de_cuenta")
    private String tipoDeCuenta;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "transaccion")
    private String transaccion;

    @Column(name = "miscelaneo")
    private String miscelaneo;

    @Column(name = "extendido")
    private String extendido;

    @Column(name = "oficina")
    private String oficina;

    private String codOficina;

    @Column(name = "estado")
    private String estado;

    @Column(name = "limite_abonos")
    private String limiteAbonos;

    @Column(name = "creadoPor")
    private String creadoPor;

    private String nombreTipoCuenta;

    private String codMoneda;


}
