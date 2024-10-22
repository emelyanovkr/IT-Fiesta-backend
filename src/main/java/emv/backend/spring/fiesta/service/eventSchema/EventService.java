package emv.backend.spring.fiesta.service.eventSchema;

import emv.backend.spring.fiesta.dto.EventDTO;
import emv.backend.spring.fiesta.model.eventSchema.Event;
import emv.backend.spring.fiesta.repository.eventSchema.EventRepository;
import emv.backend.spring.fiesta.repository.eventSchema.HostRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

  private final EventRepository eventRepository;
  private final HostRepository hostRepository;
  private final ModelMapper modelMapper;

  private static final String DATE_OF_EVENT_COLUMN_NAME = "dateOfEvent";

  public EventService(
      EventRepository eventRepository, HostRepository hostRepository, ModelMapper modelMapper) {
    this.eventRepository = eventRepository;
    this.hostRepository = hostRepository;
    this.modelMapper = modelMapper;
  }

  @PostConstruct
  public void init() {
    modelMapper
        .typeMap(Event.class, EventDTO.class)
        .addMapping(
            src -> src.getHost().getHostName(), (dest, value) -> dest.setHostName((String) value));
  }

  @Transactional(readOnly = true)
  public List<EventDTO> getEventsByUserId(Integer userId) {
    Optional<Integer> hostEntry = hostRepository.findHostIdByUserId(userId);

    List<Event> events = new ArrayList<>();
    if (hostEntry.isPresent()) {
      events =
          eventRepository.getEventsByHost_Id(
              hostEntry.get(), Sort.by(Sort.Direction.ASC, DATE_OF_EVENT_COLUMN_NAME));
    }

    return events.stream().map(event -> modelMapper.map(event, EventDTO.class)).toList();
  }

  @Transactional(readOnly = true)
  public List<EventDTO> getAllEventsSorted() {
    List<Event> events =
        eventRepository.findAll(Sort.by(Sort.Direction.ASC, DATE_OF_EVENT_COLUMN_NAME));
    return events.stream().map(event -> modelMapper.map(event, EventDTO.class)).toList();
  }

  @Transactional
  public void createEvent(EventDTO event) {
    Event newEvent = modelMapper.map(event, Event.class);
    eventRepository.save(newEvent);
  }

  @Transactional
  public void editEvent(EventDTO event, int id) throws EntityNotFoundException {
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
