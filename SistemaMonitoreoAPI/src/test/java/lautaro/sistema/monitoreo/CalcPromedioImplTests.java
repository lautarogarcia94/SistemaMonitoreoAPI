package lautaro.sistema.monitoreo;

import lautaro.sistema.monitoreo.Model.Strategy.CalcPromedioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalcPromedioImplTests {


    @Autowired
    CalcPromedioImpl calcPromedio;

    @Test
    void calcultar_colaVacia() {
        Queue<Integer> cola = new LinkedList<>();
        assertEquals(0.0, calcPromedio.calcular(cola));
    }

    @Test
    void calcultar_colaNull() {
        Queue<Integer> cola = new LinkedList<>();
        cola.add(null);
        assertEquals(0.0, calcPromedio.calcular(cola));
    }

    @Test
    void calcultar_cola1Valor() {
        Queue<Integer> cola = new LinkedList<>();
        cola.add(22);
        assertEquals(22.0, calcPromedio.calcular(cola));
    }

    @Test
    void calcultar_colaValores() {
        Queue<Integer> cola = new LinkedList<>();
        cola.add(20);
        cola.add(10);
        cola.add(15);
        assertEquals(15.0, calcPromedio.calcular(cola));
    }

    @Test
    void calcultar_colaNullConValores() {
        Queue<Integer> cola = new LinkedList<>();
        cola.add(null);
        cola.add(22);
        cola.add(35);
        cola.add(5);
        cola.add(null);
        assertEquals(20.67, calcPromedio.calcular(cola));
    }

}
