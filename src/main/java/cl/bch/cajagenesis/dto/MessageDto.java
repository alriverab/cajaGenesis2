package cl.bch.cajagenesis.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO para guardar mensajes
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MessageDto implements Serializable {

    private static final long serialVersionUID = -6533078367549236908L;

    @NonNull
    private String status;

    @NonNull
    private String message;

}