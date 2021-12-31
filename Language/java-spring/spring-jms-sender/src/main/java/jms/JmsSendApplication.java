package jms;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class JmsSendApplication {

	/*
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);

		// You could still override some of Boot's default if necessary.
		return factory;
	}
	*/

	@Bean(initMethod = "start", destroyMethod = "stop")
	public BrokerService broker() throws Exception {
		
		System.out.println("[" + Thread.currentThread().getName() + "] broker() ");
		new Throwable().printStackTrace();

		final BrokerService broker = new BrokerService();
		broker.addConnector("tcp://localhost:61616");
		broker.addConnector("vm://localhost");
		broker.setPersistent(false);
		return broker;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {

		System.out.println("[" + Thread.currentThread().getName() + "] jacksonJmsMessageConverter() ");
		new Throwable().printStackTrace();

		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	public static void main(String[] args) throws Exception {
		// Launch the application
		ConfigurableApplicationContext context = SpringApplication.run(JmsSendApplication.class, args);

		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

		// Send a message with a POJO - the template reuse the message converter
		System.out.println("[" + Thread.currentThread().getName() + "] Sending an email message.");
	
		for(int i = 0 ; i < 10 ; i++) {
			System.out.println("[" + Thread.currentThread().getName() + "] Sending Hello " + i);
			jmsTemplate.convertAndSend("mailbox", new Email("yunjongha@gmail.com", "Hello " + i));
			Thread.sleep(2* 1000);
		}

		Thread.sleep(1000 * 1000);
	}

}