package lautaro.sistema.monitoreo.demo.Controller;


import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class Controlador extends TimerTask {


    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            //llamada a metodo que maneja el HTTP POST Request
        }
    }


    /**
     * Ejecuta el timer cada 500 milisegundos para realizar los calculos sobre la cola
     * de mediciones. Empieza con un delay de 500 milisegundos en la primera ejecucion.
     */
    public void ejecutarTimer() {
        Timer temp = new Timer();
        temp.scheduleAtFixedRate(this, 500, 500);
    }
}
