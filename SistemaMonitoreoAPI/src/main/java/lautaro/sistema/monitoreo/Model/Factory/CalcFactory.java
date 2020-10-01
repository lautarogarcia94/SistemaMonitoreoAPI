package lautaro.sistema.monitoreo.Model.Factory;

import lautaro.sistema.monitoreo.Model.Strategy.CalcMaximoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcMinimoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcPromedioImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;
import org.slf4j.LoggerFactory;

/**
 * Esta clase implementa el Patrón de programacion Factory, acoplado
 * al patron Strategy implementado.
 */
public class CalcFactory {


    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CalcFactory.class);

    /**
     * Factory para la creacion de las diferentes instancias de ICalculo
     *
     * @param calculo tipo de calculo para la creacion de la instancia.
     * @return Instancia de ICalculo
     */
    public CalculoInt crearCalculo(String calculo) {
        CalculoInt calculoStrategy = null;
        if(calculo == null){
            LOGGER.warn("Se realizo llamada al metodo creacCalculo con un parametro nulo");
            return calculoStrategy;
        }

        switch (calculo.trim()) {
            case "maximo":
                calculoStrategy = new CalcMaximoImpl();
                break;
            case "minimo":
                calculoStrategy = new CalcMinimoImpl();
                break;
            case "promedio":
                calculoStrategy = new CalcPromedioImpl();
                break;
            default:
                LOGGER.warn("Parametro desconocido para la creacion del calculo");
        }
        return calculoStrategy;
    }
}
