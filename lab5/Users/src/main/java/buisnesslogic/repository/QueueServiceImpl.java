package buisnesslogic.repository;

import buisnesslogic.exception.QueueException;
import buisnesslogic.models.LoginPasswordInfo;
import buisnesslogic.models.User;

import com.google.gson.Gson;
import messaging.interfaces.EventSender;
import messaging.models.Event;

import java.util.concurrent.CompletableFuture;

public class QueueServiceImpl {

    private static final String EXCHANGE_NAME = "userExchange";
    private static final String QUEUE_TYPE = "topic";
    private static final String VALIDATE_ID_CMD = "validateId";
    private static final String ID_CMD_BASE = "id.cmds.";
    private static final String ID_VALIDATED_EVENT = "idValidated";
    private static final String USER_EVENT_BASE = "user.events.";
    private static final String USER_CREATED_EVENT = "userCreated";
    private final EventSender eventSender;
    public QueueServiceImpl(EventSender eventSender) {
        this.eventSender = eventSender;
    }


    public void publishCarBrandCreatedEvent(User user) throws QueueException {
        Event event = new Event(USER_CREATED_EVENT, user);

        try {
            eventSender.sendEvent(event, EXCHANGE_NAME, QUEUE_TYPE, USER_EVENT_BASE + USER_CREATED_EVENT);
        } catch (Exception e) {
            throw new QueueException("error publishing car brand created event");
        }
    }

    public void receiveEvent(Event event) throws QueueException {
        System.out.printf("handling event: " + event);

        if (event.getEventType().equals(ID_VALIDATED_EVENT)) {

            var idInfo = new Gson().fromJson(new Gson().toJson(event.getEventInfo()), LoginPasswordInfo.class);

        } else {

            System.out.print("event ignored: " + event.getEventType());
        }

    }
}