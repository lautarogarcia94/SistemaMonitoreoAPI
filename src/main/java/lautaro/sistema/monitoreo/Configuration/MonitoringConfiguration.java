package lautaro.sistema.monitoreo.Configuration;

import lautaro.sistema.monitoreo.Model.Factory.CalcFactory;
import lautaro.sistema.monitoreo.Model.Strategy.CalculoInt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.PriorityQueue;
import java.util.Queue;

@Configuration
public class MonitoringConfiguration {


    @Bean
    public CalcFactory calcFactory(){
        return new CalcFactory();
    }

    @Bean
    public CalculoInt calcMaximo(){
        return calcFactory().crearCalculo("maximo");
    }

    @Bean
    public CalculoInt calcMinimo(){
        return calcFactory().crearCalculo("minimo");
    }

    @Bean
    public CalculoInt calcPromedio(){
        return calcFactory().crearCalculo("promedio");
    }

    @Bean
    public Queue<Integer> Cola(){
        return new PriorityQueue<Integer>();
    }

}
