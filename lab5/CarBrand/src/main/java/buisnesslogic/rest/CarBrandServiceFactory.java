package buisnesslogic.rest;

import buisnesslogic.repository.QueueServiceImpl;
import buisnesslogic.interfaces.CarBrandService;
import messaging.interfaces.EventSender;
import messaging.rabbitmq.RabbitMqListener;
import messaging.rabbitmq.RabbitMqSender;

public class CarBrandServiceFactory {
    private static final String QUEUE_TYPE = "topic";
    private static final String EXCHANGE_NAME = "carBrandsExchange";

    private static final String ID_EVENTS = "id.events.*";
    private static final String BRAND_EVENTS = "brand.events.*";

    static CarBrandService carBrandService = null;

    public CarBrandService getService() {
        if (carBrandService != null) {
            return carBrandService;
        }

        EventSender eventSender = new RabbitMqSender("rabbitMq");
        QueueServiceImpl queueService = new QueueServiceImpl(eventSender);


        RabbitMqListener rabbitMqListener = new RabbitMqListener(queueService, "rabbitMq");
        try {
            rabbitMqListener.listen(EXCHANGE_NAME, QUEUE_TYPE, ID_EVENTS);
            rabbitMqListener.listen(EXCHANGE_NAME, QUEUE_TYPE, BRAND_EVENTS);
        } catch (Exception e) {
            throw new Error(e);
        }

        return carBrandService;
    }
}
