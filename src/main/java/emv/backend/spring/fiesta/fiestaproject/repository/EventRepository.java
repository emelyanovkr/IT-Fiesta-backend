package emv.backend.spring.fiesta.fiestaproject.repository;

import emv.backend.spring.fiesta.fiestaproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {}
