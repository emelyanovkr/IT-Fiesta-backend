package emv.backend.spring.fiesta.service;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.model.Event;
import emv.backend.spring.fiesta.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final ModelMapper modelMapper;

  private static final String DATE_OF_EVENT_COLUMN_NAME = "dateOfEvent";

  public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
    this.eventRepository = eventRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public List<EventCardDTO> getAllEventsSorted() {
    List<Event> events =
        eventRepository.findAll(Sort.by(Sort.Direction.ASC, DATE_OF_EVENT_COLUMN_NAME));
    return events.stream().map((event) -> modelMapper.map(event, EventCardDTO.class)).toList();
  }

  @Transactional
  public void createEvent(EventCardDTO event) {
    Event newEvent = modelMapper.map(event, Event.class);
    eventRepository.save(newEvent);
  }

  @Transactional
  public void editEvent(EventCardDTO event, int id) throws EntityNotFoundException {
    Event currentEntry =
        eventRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));

    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(event, currentEntry);

    eventRepository.save(currentEntry);
  }

  @Transactional
  public void deleteEvent(int id) {
    eventRepository.deleteById(id);
  }
}
