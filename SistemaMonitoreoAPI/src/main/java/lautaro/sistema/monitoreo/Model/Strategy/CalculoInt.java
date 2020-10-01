package lautaro.sistema.monitoreo.Model.Strategy;

import java.util.Queue;

/**
 * Interfaz para aplicar el Patrón Strategy.
 */
public interface CalculoInt {

    double calcular(Queue<Integer> cola);
}
