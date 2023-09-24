package buisnesslogic.rest;

import buisnesslogic.repository.QueueServiceImpl;
import buisnesslogic.interfaces.UserService;
import messaging.interfaces.EventReceiver;
import messaging.interfaces.EventSender;
import messaging.rabbitmq.RabbitMqListener;
import messaging.rabbitmq.RabbitMqSender;

public class UserServiceFactory {
    private static final String QUEUE_TYPE = "topic";
    private static final String EXCHANGE_NAME = "carModelsExchange";

    private static final String ID_EVENTS = "id.events.*";
    private static final String MODEL_EVENTS = "model.events.*";

    static UserService userService = null;

    public UserService getService() {
        if (userService != null) {
            return userService;
        }

        EventSender eventSender = new RabbitMqSender("rabbitMq");
        QueueServiceImpl queueService = new QueueServiceImpl(eventSender);


        RabbitMqListener rabbitMqListener = new RabbitMqListener((EventReceiver) queueService, "rabbitMq");
        try {
            rabbitMqListener.listen(EXCHANGE_NAME, QUEUE_TYPE, ID_EVENTS);
            rabbitMqListener.listen(EXCHANGE_NAME, QUEUE_TYPE, MODEL_EVENTS);
        } catch (Exception e) {
            throw new Error(e);
        }

        return userService;
    }
}
