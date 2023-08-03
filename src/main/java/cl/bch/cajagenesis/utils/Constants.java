package cl.bch.cajagenesis.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Constantes
 */
public class Constants {
    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    /*HEADERS NAMES*/
    public static final String H_CLIENT_IP = "IP-CLIENT";
    public static final String H_USR_NAME = "USR-NAME";
    public static final String H_USR_CODE = "USR-CODE";
    public static final String BEARER = "bearer";
    public static final String EMPTY = "";

    public static final String SPACE = " ";
    public static final String TOKEN_PARA_FIRMAS = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                                                   ".eyJjbGllbnQiOiJkNEdXIn0.YNcqx4bhDIG" +
                                                   "hBqLwCOTNs2lqITmc7eEYn-D5ggdu0VE";

    /**
     * Constructor
     */
    private Constants() {
        LOGGER.info("Constructor de Constantes");
    }
}
