package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.service.EventService;
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
  public List<EventCardDTO> getAllEvents() {
    return (eventService.getAllEventsSorted());
  }
}
