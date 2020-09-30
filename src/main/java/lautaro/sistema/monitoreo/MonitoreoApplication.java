package lautaro.sistema.monitoreo;

import lautaro.sistema.monitoreo.Controller.MonitoringRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MonitoreoApplication {

    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(MonitoreoApplication.class, args);
        MonitoringRestController controller = (MonitoringRestController) context.getBean("monitoringRestController");
        controller.ejecutarTimer();
    }
}
