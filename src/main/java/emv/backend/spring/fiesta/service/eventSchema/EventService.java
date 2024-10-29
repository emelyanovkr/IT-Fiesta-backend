package emv.backend.spring.fiesta.service.eventSchema;

import emv.backend.spring.fiesta.exception.DatabaseManagementException;
import emv.backend.spring.fiesta.exception.ForbiddenAccessException;
import emv.backend.spring.fiesta.model.eventSchema.Event;
import emv.backend.spring.fiesta.model.eventSchema.Host;
import emv.backend.spring.fiesta.repository.eventSchema.EventRepository;
import emv.backend.spring.fiesta.repository.eventSchema.HostRepository;
import jakarta.persistence.EntityNotFoundException;
import java.text.MessageFormat;
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
  private static final String EVENT_NOT_FOUND_ERROR_MSG = "EVENT NOT FOUND WITH ID: {0}";
  private static final String BAD_ACCESS_ERROR_MSG = "BAD ACCESS WITH CURRENT ID: {0}";

  public EventService(
      EventRepository eventRepository, HostRepository hostRepository, ModelMapper modelMapper) {
    this.eventRepository = eventRepository;
    this.hostRepository = hostRepository;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public List<Event> getEventsByUserId(Integer userId) {
    Optional<Integer> hostEntry = hostRepository.findHostIdByUserId(userId);

    List<Event> events = new ArrayList<>();
    if (hostEntry.isPresent()) {
      events =
          eventRepository.getEventsByHostId(
              hostEntry.get(), Sort.by(Sort.Direction.ASC, DATE_OF_EVENT_COLUMN_NAME));
    }

    return events;
  }

  @Transactional(readOnly = true)
  public List<Event> getAllEventsSorted() {
    return eventRepository.findAll(Sort.by(Sort.Direction.ASC, DATE_OF_EVENT_COLUMN_NAME));
  }

  @Transactional
  public void createEvent(Event event, Integer userId) {
    Host hostEntry = hostRepository.findHostByUserId(userId);

    event.setHost(hostEntry);
    eventRepository.save(event);
  }

  private Event getCheckedEvent(int id, Integer userId) throws ForbiddenAccessException {
    Event currentEntry =
        eventRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        MessageFormat.format(EVENT_NOT_FOUND_ERROR_MSG, id)));

    Integer storedUserId = currentEntry.getHost().getAppUser().getId();
    if (storedUserId != userId) {
      throw new ForbiddenAccessException(MessageFormat.format(BAD_ACCESS_ERROR_MSG, userId));
    }

    return currentEntry;
  }

  @Transactional
  public void editEvent(Event event, int id, Integer userId) throws DatabaseManagementException {
    Event currentEntry = getCheckedEvent(id, userId);

    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper.map(event, currentEntry);

    eventRepository.save(currentEntry);
  }

  @Transactional
  public void deleteEvent(int id, Integer userId) throws DatabaseManagementException {
    Event currentEntry = getCheckedEvent(id, userId);
    eventRepository.delete(currentEntry);
  }
}
