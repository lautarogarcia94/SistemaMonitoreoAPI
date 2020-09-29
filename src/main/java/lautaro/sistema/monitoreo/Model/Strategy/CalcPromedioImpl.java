package lautaro.sistema.monitoreo.Model.Strategy;

import org.slf4j.LoggerFactory;

import java.util.Queue;

public class CalcPromedioImpl implements  CalculoInt {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CalcPromedioImpl.class);


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
