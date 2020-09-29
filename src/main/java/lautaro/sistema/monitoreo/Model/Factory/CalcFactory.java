package lautaro.sistema.monitoreo.Model.Factory;

import lautaro.sistema.monitoreo.Model.Strategy.CalcMaximoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcMinimoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcPromedioImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;

public class CalcFactory {


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
