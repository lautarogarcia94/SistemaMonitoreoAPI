package lautaro.sistema.monitoreo.Model.Strategy;

import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * Esta clase implementa la interfaz CalculoInt, para realizar el calculo del minimo valor de una cola
 */
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

        if (cola.size() == 0) {
            LOGGER.warn("No hay elementos en la cola para calcular el promedio");
            return 0.0;
        }
        for (Integer x : cola) {
            num += x;
        }
        return num / cola.size();
    }
}
