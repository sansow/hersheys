import javax.jms.*;
import org.apache.qpid.jms.JmsConnectionFactory;

public class AMQProducer {
    public static void main(String[] args) {
        String brokerUrl = System.getenv("BROKER_URL");
        String queueName = System.getenv("QUEUE_NAME");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        int messageCount = Integer.parseInt(System.getenv().getOrDefault("MESSAGE_COUNT", "10"));

        try {
            // Create a connection factory
            JmsConnectionFactory factory = new JmsConnectionFactory(brokerUrl);

            // Create a connection
            Connection connection = factory.createConnection(username, password);
            connection.start();

            // Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a queue
            Queue queue = session.createQueue(queueName);

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            // Send messages
            for (int i = 1; i <= messageCount; i++) {
                TextMessage message = session.createTextMessage("Message " + i);
                producer.send(message);
                System.out.println("Sent message: " + message.getText());
            }

            // Clean up
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
