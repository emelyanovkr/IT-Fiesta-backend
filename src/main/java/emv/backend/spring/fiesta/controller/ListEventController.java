package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventDTO;
import emv.backend.spring.fiesta.service.eventSchema.EventService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListEventController {

  private final EventService eventService;

  public ListEventController(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping("/events")
  public List<EventDTO> getAllEvents() {
    return (eventService.getAllEventsSorted());
  }
}
