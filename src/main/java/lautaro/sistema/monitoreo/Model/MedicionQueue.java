package lautaro.sistema.monitoreo.Model;

import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;
import lombok.Getter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class MedicionQueue {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MedicionQueue.class);

    @Value("${cteM}")
    private double cteM;

    @Value("${cteS}")
    private double cteS;

    @Getter
    private Queue<Integer> cola;

    @Getter
    private CalculoInt calcMaximo;

    @Getter
    private CalculoInt calcMinimo;

    @Getter
    private CalculoInt calcPromedio;

    @Autowired
    public void setCola(Queue<Integer> cola) {
        this.cola = cola;
    }

    @Autowired
    public void setCalcMaximo(CalculoInt calcMaximo) {
        this.calcMaximo = calcMaximo;
    }

    @Autowired
    public void setCalcMinimo(CalculoInt calcMinimo) {
        this.calcMinimo = calcMinimo;
    }

    @Autowired
    public void setCalcPromedio(CalculoInt calcPromedio) {
        this.calcPromedio = calcPromedio;
    }


    /**
     * Agrega un numero a la cola de mediciones, y loguea la accion en un mensaje
     * @param num int Numero a agregar
     */
    public void agregarCola(int num) {
        synchronized (cola) {
            cola.add(num);
            LOGGER.info("Se agrega numero " + num);
        }
    }

    /**
     *
     */

    /**
     * Realiza los llamados para realizar calcular el minimo, el maximo y el promedio de los datos almacenados
     * en la cola. Ademas llama al metodo encargado de realizar el control de los resultados, y por ultimo borra
     * a cola de datos.
     * Por ultimo genera una instancia de la clase Resultado, para retornar los datos calculados.
     * @return Resultado
     */
    public Resultado realizarCalculos() {
        if (colaVacia()) {
            LOGGER.warn("Sin elementos para calcular");
            Resultado resultado = new Resultado();
            return resultado;
        }
        String diferencia;
        String promedio;
        synchronized (cola) {
            double max = calcMaximo.calcular(cola);
            double min = calcMinimo.calcular(cola);
            diferencia = controlar(max - min, "diferencia");
            double prom = calcPromedio.calcular(cola);
            promedio = controlar(prom, "promedio");
            borrarCola();
        }
        Resultado resultado = new Resultado(diferencia, promedio);
        return resultado;
    }

    /**
     * Borra la cola de datos
     */
    private void borrarCola() {
        cola.clear();
    }


    /**
     * Controla si la cola esta vacia, retorna true si esta vacia y false si no lo esta
     *
     * @return boolean
     */
    private boolean colaVacia() {
        return cola.size() == 0;
    }

    /**
     * Controla los calculos realizados y loguea un mensaje error en caso que sean mayores a los
     * parametros cteM y cteS.
     */
    private String controlar(double calculo, String tipo) {
        String mensaje = "";
        switch (tipo) {
            case "diferencia":
                mensaje = "La diferencia es " + calculo;
                if (cteS < calculo) {
                    mensaje += ", y es mayor al valor " + cteS + " seteado";
                    LOGGER.error(mensaje);
                }
                break;
            case "promedio":
                mensaje = "El promedio es " + calculo;
                if (cteM < calculo) {
                    mensaje += ", y es mayor al valor " + cteM + " seteado";
                    LOGGER.error(mensaje);
                }
        }
        return mensaje;
    }
}
