package buisnesslogic.interfaces;

import buisnesslogic.exception.QueueException;
import buisnesslogic.models.CarModel;

public interface QueueService {

    void publishCarBrandCreatedEvent(CarModel carModel) throws QueueException;
}
