package emv.backend.spring.fiesta.repository.eventSchema;

import emv.backend.spring.fiesta.model.eventSchema.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {}
