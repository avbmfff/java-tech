package buisnesslogic.repository;

import buisnesslogic.exception.QueueException;
import buisnesslogic.interfaces.QueueService;
import buisnesslogic.models.CarModel;
import buisnesslogic.models.IdInfo;
import com.google.gson.Gson;
import messaging.interfaces.EventReceiver;
import messaging.interfaces.EventSender;
import messaging.models.Event;

public class QueueServiceImpl implements EventReceiver, QueueService {

    private static final String EXCHANGE_NAME = "carModelExchange";
    private static final String QUEUE_TYPE = "topic";
    private static final String ID_VALIDATED_EVENT = "idValidated";
    private static final String CAR_MODEL_EVENT_BASE = "carModel.events.";
    private static final String CAR_MODEL_CREATED_EVENT = "carModelCreated";
    private final EventSender eventSender;
    public QueueServiceImpl(EventSender eventSender) {
        this.eventSender = eventSender;
    }

    @Override
    public void publishCarBrandCreatedEvent(CarModel carModel) throws QueueException {
        Event event = new Event(CAR_MODEL_CREATED_EVENT, carModel);

        try {
            eventSender.sendEvent(event, EXCHANGE_NAME, QUEUE_TYPE, CAR_MODEL_EVENT_BASE + CAR_MODEL_CREATED_EVENT);
        } catch (Exception e) {
            throw new QueueException("error publishing car brand created event");
        }
    }

    @Override
    public void receiveEvent(Event event) throws QueueException {
        System.out.printf("handling event: " + event);

        if (event.getEventType().equals(ID_VALIDATED_EVENT)) {

            var idInfo = new Gson().fromJson(new Gson().toJson(event.getEventInfo()), IdInfo.class);

        } else {

            System.out.print("event ignored: " + event.getEventType());
        }

    }
}