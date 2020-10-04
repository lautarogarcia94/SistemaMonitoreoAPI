package lautaro.sistema.monitoreo.demo.Controller;


import lautaro.sistema.monitoreo.demo.HTTPRequest.RequestMonitoreo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Timer;
import java.util.TimerTask;

@Controller
public class Controlador extends TimerTask {

    private RequestMonitoreo requestMonitoreo;

    @Autowired
    public void setRequestMonitoreo(RequestMonitoreo requestMonitoreo){
        this.requestMonitoreo = requestMonitoreo;
    }

    private void insertarMedicion(int medicion){
        requestMonitoreo.postMedicion(medicion);
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            insertarMedicion((int) (Math.random() * 200));
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
