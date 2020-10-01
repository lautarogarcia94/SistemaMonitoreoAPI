package lautaro.sistema.monitoreo.Model.Factory;

import lautaro.sistema.monitoreo.Model.Strategy.CalcMaximoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcMinimoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcPromedioImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;

/**
 * Esta clase implementa el Patrón de programacion Factory, acoplado
 * al patron Strategy implementado.
 */
public class CalcFactory {

    /**
     * Factory para la creacion de las diferentes instancias de ICalculo
     *
     * @param calculo tipo de calculo para la creacion de la instancia.
     * @return Instancia de ICalculo
     */
    public CalculoInt crearCalculo(String calculo) {
        CalculoInt calculoStrategy = null;

        switch (calculo) {
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
                calculoStrategy = new CalcPromedioImpl();
                break;
        }
        return calculoStrategy;
    }
}
