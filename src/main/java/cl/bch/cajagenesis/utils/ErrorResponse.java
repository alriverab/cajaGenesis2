package cl.bch.cajagenesis.utils;

import cl.bch.cajagenesis.dto.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controla los errores en las respuestas del servidor
 */
public class ErrorResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorResponse.class);

    /**
     * Constructor
     */
    private ErrorResponse() {
        LOGGER.debug("Error response constructor");
    }

    /**
     * Genera el mensaje de error
     * @param code código del error
     * @param message mensaje del error
     * @return objeto del mensaje de error
     */
    public static MessageDto generateErrorMsg(String code, String message) {
        return new MessageDto(code, message);
    }
}
