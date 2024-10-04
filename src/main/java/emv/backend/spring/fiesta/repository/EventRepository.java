package emv.backend.spring.fiesta.repository;

import emv.backend.spring.fiesta.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {}
