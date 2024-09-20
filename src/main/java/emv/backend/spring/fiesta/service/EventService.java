package emv.backend.spring.fiesta.service;

import emv.backend.spring.fiesta.model.Event;
import emv.backend.spring.fiesta.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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

  @Transactional
  public List<Event> getAllEvents() {
    List<Event> events = eventRepository.findAll();
    events.sort(Comparator.comparing(Event::getDateOfEvent));
    return events;
  }
}
