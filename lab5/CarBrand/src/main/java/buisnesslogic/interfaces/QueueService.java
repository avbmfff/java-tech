package buisnesslogic.interfaces;

import buisnesslogic.exception.QueueException;
import buisnesslogic.models.CarBrand;
import buisnesslogic.models.IdInfo;
import org.springframework.data.repository.query.QueryCreationException;

public interface QueueService {
    void publishCarBrandCreatedEvent(CarBrand carBrand) throws QueueException;
}
