package lautaro.sistema.monitoreo.Controller;

import lautaro.sistema.monitoreo.Model.request.MedicionRquestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("monitoring")
public class MonitoringRestController {

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> createUser(@Valid @RequestBody MedicionRquestModel medicion) {
        return new ResponseEntity<>("La medicion ingresada es: "+medicion.getMedicion(), HttpStatus.OK);
    }
}
