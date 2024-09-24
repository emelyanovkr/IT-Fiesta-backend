package emv.backend.spring.fiesta.service;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.model.Event;
import emv.backend.spring.fiesta.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
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
  public List<EventCardDTO> getAllEventsSorted() {
    List<Event> events = eventRepository.findAll();
    return events.stream()
        .map((event) -> modelMapper.map(event, EventCardDTO.class))
        .sorted(Comparator.comparing(EventCardDTO::getDateOfEvent))
        .toList();
  }

  @Transactional
  public void createEvent(EventCardDTO event) {
    Event newEvent = modelMapper.map(event, Event.class);
    eventRepository.save(newEvent);
  }

  @Transactional
  public void editEvent(EventCardDTO event, int id) {
    Event currentEntry =
        eventRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Event not found with id: " + id)); // TODO: custom exceptions

    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(event, currentEntry);

    eventRepository.save(currentEntry);
  }

  @Transactional
  public void deleteEvent(int id) {
    eventRepository.deleteById(id);
  }
}
