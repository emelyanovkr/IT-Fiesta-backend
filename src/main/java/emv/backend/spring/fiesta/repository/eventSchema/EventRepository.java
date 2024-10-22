package emv.backend.spring.fiesta.repository.eventSchema;

import emv.backend.spring.fiesta.model.eventSchema.Event;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

  List<Event> getEventsByHost_Id(Integer hostId, Sort sorting);
}
