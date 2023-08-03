package cl.bch.cajagenesis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * Clase que almacena funciones generales o útiles
 * @author 160k
 */
@PropertySource({"file:${APPS_PROPS}/Config/CAJAGEN-properties.properties"})
public class FuncionesGenerales {

    private static final Logger LOGGER = LoggerFactory.getLogger(FuncionesGenerales.class);


    @Value("${key.secret}")
    private static String secretKy;

    private FuncionesGenerales()
    {
        LOGGER.info("Inicio de FuncionesGenerales");
    }

    /*********************************************************************
     * Nombre funcion: parseDate.........................................*
     * Action:Validar el formato de una fecha YYYY-MM-DD HH:mm:ss........*
     * Inp:@value:String => texto a formatear............................*
     * Out::Date => formato date.........................................*
     *********************************************************************/
    public static Date parseDate(String value)
    {
        Date result = null;
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
        }
        catch (ParseException ex) {
            result = null;
        }
        return result;
    }

    /**
     * Obtiene el mensaje del LOG para registrar la acción de un usuario
     * @param CreadoPor nombre de usuario quien generó el log
     * @return mensaje que se registrará en el log
     */
    public static String getDateLog(String creadoPor)
    {
        return (new SimpleDateFormat("ddMMyyyy HH:mm:ss").format(new Date())) +"|" + creadoPor;
    }

    /************************************************************************************************************
     * Nombre funcion: esAlfaNumericoGuion......................................................................*
     * Action:Validar Validar si la cadena de entrada es alfanumeroco-guión (letras, numeros y guión unicamente)*
     * Inp:@codigo:String => texto a verificar..................................................................*
     * Out::boolean => true/false dependiendo si es o no alfanumerico...........................................*
     ************************************************************************************************************/
    public static boolean esAlfaNumericoGuion(String codigo)
    {
        Pattern pat = Pattern.compile("^[a-zA-Z0-9-]*$");
        Matcher mat = pat.matcher(codigo);

        return mat.matches();
    }

    /*************************************************************************
     * Nombre funcion: esNumerico............................................*
     * Action:Validar si la cadena de entrada es numeroco....................*
     * Inp:@texto:String => texto a verificar................................*
     * Out::boolean => true/false dependiendo si es o no alfanumerico........*
     *************************************************************************/
    public static boolean esNumerico(String texto)
    {
        Pattern pat = Pattern.compile("\\d");
        Matcher mat = pat.matcher(texto);

        return mat.matches();
    }

}
