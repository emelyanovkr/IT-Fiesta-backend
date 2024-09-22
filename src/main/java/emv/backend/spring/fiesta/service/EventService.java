package emv.backend.spring.fiesta.service;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.model.Event;
import emv.backend.spring.fiesta.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final ModelMapper modelMapper;

  public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
    this.eventRepository = eventRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public void saveEventDetails(Event event) {
    eventRepository.save(event);
  }

  @Transactional
  public List<EventCardDTO> getAllEventsCards() {
    List<Event> events = eventRepository.findAll();
    return events.stream()
        .map((event) -> modelMapper.map(event, EventCardDTO.class))
        .sorted(Comparator.comparing(EventCardDTO::getDateOfEvent))
        .toList();
  }

  @Transactional
  public void deleteEvent(int id) {
    eventRepository.deleteById(id);
  }
}
