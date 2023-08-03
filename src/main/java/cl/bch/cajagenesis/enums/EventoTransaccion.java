package cl.bch.cajagenesis.enums;

/**
 * Eventos de transacciones permitidos
 * @author 160k
 */
public enum EventoTransaccion { 
 
   RECEPCION("Recepción")
 , CARGO("Cargo")
 , CONSULTA("Consulta")
 , NOTIFICACION("Notificacion")
 , CAMBIO_ESTADO("Cambio Estado")
 , REINTENTO("Reintento")
 , REINTENTO_MANUAL("Reintento Manual");
 
 private final String value; 
 private EventoTransaccion(String val) { this.value = val; }
 public String getValue() { return this.value; }
}