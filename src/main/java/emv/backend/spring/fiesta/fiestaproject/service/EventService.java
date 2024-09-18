package emv.backend.spring.fiesta.fiestaproject.service;

import emv.backend.spring.fiesta.fiestaproject.model.Event;
import emv.backend.spring.fiesta.fiestaproject.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Transactional
  public void saveEventDetails(Event event) {
    eventRepository.save(event);
  }
}
