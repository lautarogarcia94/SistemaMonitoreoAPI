package lautaro.sistema.monitoreo.Controller;

import lautaro.sistema.monitoreo.Model.MedicionQueue;
import lautaro.sistema.monitoreo.Model.Resultado;
import lautaro.sistema.monitoreo.Model.connection.ResultadoDAO;
import lautaro.sistema.monitoreo.Model.request.MedicionRquestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("monitoring")
public class MonitoringRestController extends TimerTask {

    private MedicionQueue colaMedicion;

    private ResultadoDAO resultadoDAO;

    @Autowired
    public void setResultadoDAO(ResultadoDAO resultadoDAO) {
        this.resultadoDAO = resultadoDAO;
    }

    @Autowired
    public void setColaMedicion(MedicionQueue colaMedicion) {
        this.colaMedicion = colaMedicion;
    }


    /**
     * Este metodo retorna una lista de los resultados generados por los calculos, con formato JSON o XML
     * segun sea solicitado en el GET request sobre el endpoint .../monitoring
     *
     * @return List<Resultado>
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Resultado> getUsers() {
        return resultadoDAO.getAllResultados();
    }

    /**
     * Registra una nueva medicion, cuando recibe un POST request en el endpoint .../monitoring.
     * La medicion debe ser enviada en el cuerpo de la solicitud HTTP, este metodo espera un mensaje
     * como el siguiente:
     * {
     * "medicion": "10"
     * }
     * <p>
     * Puede recibir este parametro tanto en un JSON, como en un XML.
     *
     * @param medicion numero que será añadido como medicion
     * @return HTTP Created message - 201
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> createUser(@Valid @RequestBody MedicionRquestModel medicion) {
        colaMedicion.agregarCola(medicion.getMedicion());
        return new ResponseEntity<>("La medicion ingresada es: " + medicion.getMedicion(), HttpStatus.CREATED);
    }

    /**
     * Cuando se da la interrupcion por el Timer, este metodo llama  al metodo realizarCalculos() del objeto
     * colaMedicion y agrega el resultado a una lista de resultados.
     */
    @Override
    public void run() {
        Resultado resultado = colaMedicion.realizarCalculos();
        resultadoDAO.insertarResultado(resultado);
    }

    /**
     * Ejecuta el timer cada 30 segundos para realizar los calculos sobre la cola
     * de mediciones. Empieza con un delay de 30 segundos en la primera ejecucion.
     */
    public void ejecutarTimer() {
        Timer temp = new Timer();
        temp.scheduleAtFixedRate(this, 1000 * 30, 1000 * 30);
    }
}
