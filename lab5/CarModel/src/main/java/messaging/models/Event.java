package messaging.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Event {
    private String eventType;
    private Object eventInfo;

    public Event(String eventType) {
        this.eventInfo = null;
        this.eventType = eventType;
    }

    public String toString() {
        return String.format("event (%s, %s)", eventType, eventInfo);
    }

}
