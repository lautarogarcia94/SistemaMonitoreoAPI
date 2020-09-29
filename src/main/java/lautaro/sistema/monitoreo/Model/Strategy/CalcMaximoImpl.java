package lautaro.sistema.monitoreo.Model.Strategy;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class CalcMaximoImpl implements CalculoInt {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CalcMaximoImpl.class);

    @Override
    public double calcular(Queue<Integer> cola) {

        if (cola.size() == 0) {
            LOGGER.warn("No hay elementos en la cola para calcular el promedio");
            return 0.0;
        }

        int num = cola.peek();
        for (Integer x : cola) {
            if (num < x) {
                num = x;
            }
        }
        LOGGER.info("El valor Maximo es: " + num);
        return num;
    }
}

