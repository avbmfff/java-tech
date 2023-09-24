package buisnesslogic.rest;

import buisnesslogic.repository.QueueServiceImpl;
import buisnesslogic.interfaces.CarModelService;
import messaging.interfaces.EventSender;
import messaging.rabbitmq.RabbitMqListener;
import messaging.rabbitmq.RabbitMqSender;

public class CarModelServiceFactory {
    private static final String QUEUE_TYPE = "topic";
    private static final String EXCHANGE_NAME = "carModelsExchange";

    private static final String ID_EVENTS = "id.events.*";
    private static final String MODEL_EVENTS = "model.events.*";

    static CarModelService carModelService = null;

    public CarModelService getService() {
        if (carModelService != null) {
            return carModelService;
        }

        EventSender eventSender = new RabbitMqSender("rabbitMq");
        QueueServiceImpl queueService = new QueueServiceImpl(eventSender);


        RabbitMqListener rabbitMqListener = new RabbitMqListener(queueService, "rabbitMq");
        try {
            rabbitMqListener.listen(EXCHANGE_NAME, QUEUE_TYPE, ID_EVENTS);
            rabbitMqListener.listen(EXCHANGE_NAME, QUEUE_TYPE, MODEL_EVENTS);
        } catch (Exception e) {
            throw new Error(e);
        }

        return carModelService;
    }
}
