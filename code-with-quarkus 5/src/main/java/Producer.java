import javax.jms.*;
import org.apache.qpid.jms.JmsConnectionFactory;

public class AMQConsumer {
    public static void main(String[] args) {
        String brokerUrl = System.getenv("BROKER_URL");
        String queueName = System.getenv("QUEUE_NAME");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        long receiveTimeout = Long.parseLong(System.getenv().getOrDefault("RECEIVE_TIMEOUT", "5000"));

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

            // Create a consumer
            MessageConsumer consumer = session.createConsumer(queue);

            // Receive messages
            System.out.println("Waiting for messages...");
            while (true) {
                Message message = consumer.receive(receiveTimeout);
                if (message == null) {
                    System.out.println("No more messages received after timeout.");
                    break;
                }
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println("Received: " + textMessage.getText());
                }
            }

            // Clean up
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}