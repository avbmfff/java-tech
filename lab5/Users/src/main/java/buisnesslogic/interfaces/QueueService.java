package buisnesslogic.interfaces;

import buisnesslogic.models.LoginPasswordInfo;
import buisnesslogic.models.User;
import org.springframework.data.repository.query.QueryCreationException;

public interface QueueService {

    void publishCarBrandCreatedEvent(User user) throws IllegalArgumentException;
}
