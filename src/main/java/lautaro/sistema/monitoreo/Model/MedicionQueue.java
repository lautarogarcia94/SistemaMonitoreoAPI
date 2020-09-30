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


    public void agregarCola(int num) {

        synchronized (cola) {
            cola.add(num);
            LOGGER.info("Se agrega numero " + num);
        }
    }

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

    private void borrarCola() {
        cola.clear();
    }


    private boolean colaVacia() {
        return cola.peek() == null;
    }

    private String controlar(double calculo, String tipo) {

        switch (tipo) {
            case "diferencia":
                if (cteS < calculo) {
                    LOGGER.error("La diferencia es " + calculo + ", y es mayor al valor " + cteS + " seteado");
                    return "La diferencia es " + calculo + ", y es mayor al valor " + cteS + " seteado";
                }
                LOGGER.info("La diferencia es " + calculo + ".");
                return "La diferencia es " + calculo + ".";
            case "promedio":
                if (cteM < calculo) {
                    LOGGER.error("El promedio es " + calculo + ", y es mayor al valor " + cteM + " seteado");
                    return "El promedio es " + calculo + ", y es mayor al valor " + cteM + " seteado";
                }
                LOGGER.info("El promedio es " + calculo + ".");
                return "El promedio es " + calculo + ".";
        }
        return "";
    }
}
