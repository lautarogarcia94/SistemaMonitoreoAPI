package lautaro.sistema.monitoreo.Model;

import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class MedicionQueue {

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
        }
    }

    public void realizarCalculos() {
        if (colaVacia()) {
            return;
        }
        synchronized (cola) {
            calcular();
            borrarCola();
        }

    }

    private void borrarCola() {
        cola.clear();
    }

    private void calcular() {
        double max = calcMaximo.calcular(cola);
        double min = calcMinimo.calcular(cola);
        String primerControl = controlar(max - min, "diferencia");
        double prom = calcPromedio.calcular(cola);
        String segundoControl = controlar(prom, "promedio");
    }

    private boolean colaVacia() {
        return cola.peek() == null;
    }

    private String controlar(double calculo, String tipo) {

        switch (tipo) {
            case "diferencia":
                if (cteS < calculo) {
                    return "La diferencia es " + calculo + ", y es mayor al valor " + cteS + " seteado";
                }
                return "La diferencia es " + calculo + ".";
            case "promedio":
                if (cteM < calculo) {
                    return "El promedio es " + calculo + ", y es mayor al valor " + cteM + " seteado";
                    // LOGGER.error("El promedio es "+calculo+", y es mayor al valor "+ cteM +" seteado");
                }
                return "El promedio es " + calculo + ".";
        }
        return "";
    }
}
