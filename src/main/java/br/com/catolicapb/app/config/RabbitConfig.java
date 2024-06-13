package br.com.catolicapb.app.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue patientQueue() {
        return new Queue("patient.queue", true);
    }

    @Bean
    public TopicExchange patientExchange() {
        return new TopicExchange("patient.exchange");
    }

    @Bean
    public Binding patientBinding(Queue patientQueue, TopicExchange patientExchange) {
        return BindingBuilder.bind(patientQueue).to(patientExchange).with("patient.#");
    }
}
