package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventDTO;
import emv.backend.spring.fiesta.service.eventSchema.EventService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListEventController {

  private final EventService eventService;
  private final ModelMapper modelMapper;

  public ListEventController(EventService eventService, ModelMapper modelMapper) {
    this.eventService = eventService;
    this.modelMapper = modelMapper;
  }

  // TODO: Pagination
  @GetMapping("/events")
  public List<EventDTO> getAllEvents() {
    return (eventService.getAllEventsSorted().stream()
        .map(event -> modelMapper.map(event, EventDTO.class))
        .toList());
  }
}
