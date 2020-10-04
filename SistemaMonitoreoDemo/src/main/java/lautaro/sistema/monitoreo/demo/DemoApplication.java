package lautaro.sistema.monitoreo.demo;

import lautaro.sistema.monitoreo.demo.Controller.Controlador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args){

		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Controlador controller = (Controlador) context.getBean("controlador");
		controller.ejecutarTimer();


	}

}
