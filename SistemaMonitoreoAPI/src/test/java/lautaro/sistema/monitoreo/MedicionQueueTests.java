package lautaro.sistema.monitoreo;

import lautaro.sistema.monitoreo.Model.MedicionQueue;
import lautaro.sistema.monitoreo.Model.Resultado;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class MedicionQueueTests {

    @Autowired
    private MedicionQueue medicionQueue;

    @Test
    void _1_realizarCalculo() {
        Resultado resultado = medicionQueue.realizarCalculos();
        assertTrue(resultado.getDiferencia().equals("--"));
        assertTrue(resultado.getPromedio().equals("--"));
    }

    @Test
    void _2_agregarNumero() {
        medicionQueue.agregarCola(3);
        assertEquals(1, medicionQueue.getCola().size());
    }

    @Test
    void _3_realizarCalculo() {

        medicionQueue.agregarCola(6);
        medicionQueue.agregarCola(5);
        medicionQueue.agregarCola(10);
        Resultado resultado = medicionQueue.realizarCalculos();
        assertTrue(resultado.getDiferencia().equalsIgnoreCase("La diferencia es 7.0"));
        assertTrue(resultado.getPromedio().equalsIgnoreCase("El promedio es 6.0"));
    }

    @Test
    void _4_realizarCalculo() {

        medicionQueue.agregarCola(6);
        medicionQueue.agregarCola(5);
        medicionQueue.agregarCola(9);
        Resultado resultado = medicionQueue.realizarCalculos();
        assertTrue(resultado.getDiferencia().equalsIgnoreCase("La diferencia es 4.0"));
        assertTrue(resultado.getPromedio().equalsIgnoreCase("El promedio es 6.67"));
    }
}
