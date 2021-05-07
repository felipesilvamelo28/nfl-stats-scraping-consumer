package br.com.nflscrapping.consumer;

import br.com.nflscrapping.consumer.rabbitmq.ConsumerRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsumerApplication {

	@Autowired
	ConsumerRabbit consumerRabbit;

	@Bean
	void consumir() throws Exception {
		System.out.println("Escutando");
		consumerRabbit.processMessage();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
