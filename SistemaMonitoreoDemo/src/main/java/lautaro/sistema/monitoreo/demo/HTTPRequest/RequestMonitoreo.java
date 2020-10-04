package lautaro.sistema.monitoreo.demo.HTTPRequest;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestMonitoreo {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RequestMonitoreo.class);

    public void postMedicion(int medicion) {
        HttpResponse<String> response = null;
        try {
            Unirest.setTimeouts(0, 0);
            response = Unirest.post("http://localhost:8080/monitoring")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .body("{\r\n    \"medicion\": \"" + medicion + "\"\r\n}")
                    .asString();


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        if (response != null) {
            LOGGER.info(response.getBody());
        }
    }

}
