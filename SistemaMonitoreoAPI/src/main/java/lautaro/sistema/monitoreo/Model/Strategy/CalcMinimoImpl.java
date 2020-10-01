package lautaro.sistema.monitoreo.Model.Strategy;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Queue;

/**
 * Esta clase implementa la interfaz CalculoInt, para realizar el calculo del minimo valor de una cola
 */
@Component
public class CalcMinimoImpl implements CalculoInt {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CalcMinimoImpl.class);

    /**
     * Calcula el minimo valor dentro de la cola pasada por parametro. En caso de recibir
     * una cola vacia, loguea un warning y retorna el valor 0.0
     *
     * @param cola Queue<Integer> Cola sobre la cual se va a buscar el minimo
     * @return double Minimo de los valores de la cola.
     */
    @Override
    public double calcular(Queue<Integer> cola) {

        if (cola.size() == 0) {
            LOGGER.warn("No hay elementos en la cola para calcular el promedio");
            return 0.0;
        }

        boolean  asigneNum = false;
        int num = 0;
        for (Integer x : cola) {
            if(x == null){
                LOGGER.error("En la cola se encontro un valor nulo");
                continue;
            }

            if(asigneNum){
                if (num > x) {
                    num = x;
                }
            }else{
                num = x;
                asigneNum = true;
            }
        }
        return num;
    }
}