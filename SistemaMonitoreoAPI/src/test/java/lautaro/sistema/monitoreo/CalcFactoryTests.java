package lautaro.sistema.monitoreo;

import lautaro.sistema.monitoreo.Model.Factory.CalcFactory;
import lautaro.sistema.monitoreo.Model.Strategy.CalcMaximoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcMinimoImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalcPromedioImpl;
import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CalcFactoryTests {

    @Autowired
    private CalcFactory calcFactory;

    @Test
    void crear_CalcMinimoImpl() {
        CalculoInt operacion = calcFactory.crearCalculo("minimo");
        assertTrue(operacion.getClass().isInstance(new CalcMinimoImpl()));
    }

    @Test
    void crear_CalcMaximoImpl() {
        CalculoInt calculo = calcFactory.crearCalculo("maximo");
        assertTrue(calculo.getClass().isInstance(new CalcMaximoImpl()));
    }

    @Test
    void crear_CalcPromedioImpl_1() {
        CalculoInt calculo = calcFactory.crearCalculo("promedio");
        assertTrue(calculo.getClass().isInstance(new CalcPromedioImpl()));
    }

    @Test
    void crear_CalcPromedioImpl_2() {
        CalculoInt calculo = calcFactory.crearCalculo(" promedio");
        assertTrue(calculo.getClass().isInstance(new CalcPromedioImpl()));
    }

    @Test
    void crear_CalcPromedioImpl_3() {
        CalculoInt calculo = calcFactory.crearCalculo(" promedio ");
        assertTrue(calculo.getClass().isInstance(new CalcPromedioImpl()));
    }

    @Test
    void crear_CalcPromedioImpl_4() {
        CalculoInt calculo = calcFactory.crearCalculo("promedio ");
        assertTrue(calculo.getClass().isInstance(new CalcPromedioImpl()));
    }

    @Test
    void crear_CalcPromedioImpl_5() {
        CalculoInt calculo = calcFactory.crearCalculo("prom edio ");
        assertNull(calculo);
    }

    @Test
    void crear_CalcNull() {
        CalculoInt calculo = calcFactory.crearCalculo(null);
        assertNull(calculo);
    }

    @Test
    void crear_CalcCualquierParam() {
        CalculoInt calculo = calcFactory.crearCalculo("abcde");
        assertNull(calculo);
    }
}
