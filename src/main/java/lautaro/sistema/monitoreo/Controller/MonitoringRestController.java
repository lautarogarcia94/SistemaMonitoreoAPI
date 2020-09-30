package lautaro.sistema.monitoreo.Controller;

import lautaro.sistema.monitoreo.Model.MedicionQueue;
import lautaro.sistema.monitoreo.Model.Resultado;
import lautaro.sistema.monitoreo.Model.request.MedicionRquestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping("monitoring")
public class MonitoringRestController extends TimerTask {

    private ArrayList<Resultado> lista = new ArrayList<>();

    private MedicionQueue colaMedicion;

    @Autowired
    public void setColaMedicion(MedicionQueue colaMedicion) {
        this.colaMedicion = colaMedicion;
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Resultado> getUsers() {
        return lista;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> createUser(@Valid @RequestBody MedicionRquestModel medicion) {
        colaMedicion.agregarCola(medicion.getMedicion());
        return new ResponseEntity<>("La medicion ingresada es: "+medicion.getMedicion(), HttpStatus.OK);
    }

    @Override
    public void run() {
        Resultado resultado = colaMedicion.realizarCalculos();
        lista.add(resultado);
    }

    public void ejecutarTimer(){
        Timer temp = new Timer();
        temp.scheduleAtFixedRate(this,1000*30,1000*30);
    }
}
