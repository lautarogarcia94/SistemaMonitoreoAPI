package lautaro.sistema.monitoreo.Model.Strategy;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Queue;

/**
 * Esta clase implementa la interfaz CalculoInt, para realizar el calculo del minimo valor de una cola
 */
@Component
public class CalcPromedioImpl implements CalculoInt {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CalcPromedioImpl.class);

    /**
     * Calcula el promedio de los valor dentro de la cola pasada por parametro. En caso de recibir
     * una cola vacia, loguea un warning y retorna el valor 0.0
     *
     * @param cola Queue<Integer> Cola sobre la cual se desea calcular el promedio.
     * @return double Promedio de los valores de la cola.
     */
    @Override
    public double calcular(Queue<Integer> cola) {
        Double num = 0.0;
        int size = cola.size();

        if (size == 0) {
            LOGGER.warn("No hay elementos en la cola para calcular el promedio");
            return 0.0;
        }
        for (Integer x : cola) {
            if(x == null){
                LOGGER.error("En la cola se encontro un valor nulo");
                size--;
                continue;
            }
            num += x;
        }
        double promedio = num/size;
        return Math.round(promedio*100)/100.0;
    }
}
