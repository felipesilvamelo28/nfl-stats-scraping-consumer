package br.com.nflscrapping.consumer.rabbitmq;

import br.com.nflscrapping.consumer.entity.Fumble;
import br.com.nflscrapping.consumer.service.FumbleService;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRabbit {

    @Autowired
    FumbleService service;

    public void processMessage() throws Exception {
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null) uri = "amqps://gljpxxmk:YQA6ACRYs3kLSist2O0n2tWm1eubswtN@clam.rmq.cloudamqp.com/gljpxxmk";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);

        //Recommended settings
        factory.setConnectionTimeout(30000);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queue = "hello";     //queue name
        boolean durable = false;    //durable - RabbitMQ will never lose the queue if a crash occurs
        boolean exclusive = false;  //exclusive - if queue only will be used by one connection
        boolean autoDelete = false; //autodelete - queue is deleted when last consumer unsubscribes

        channel.queueDeclare(queue, durable, exclusive, autoDelete, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            Fumble fumble = new Gson().fromJson(message, Fumble.class);
            service.save(fumble);
        };

        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
        //channel.close();
        //connection.close();
    }
}
