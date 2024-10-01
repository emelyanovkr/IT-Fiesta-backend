package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.service.EventService;
import emv.backend.spring.fiesta.util.FailedValidationResponseHandler;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage")
public class ManageEventController {

  private final EventService eventService;

  public ManageEventController(EventService eventService) {
    this.eventService = eventService;
  }

  // TODO: reconsider to move endpoint
  @GetMapping("/events")
  public ResponseEntity<List<EventCardDTO>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEventsSorted());
  }

  @PostMapping("/create")
  public ResponseEntity<Map<String, String>> createEvent(
      @RequestBody @Valid EventCardDTO event, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }

    eventService.createEvent(event);
    return ResponseEntity.ok(Map.of("message", "Event created: " + event.getEventName()));
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<Map<String, String>> editEvent(
      @PathVariable int id, @RequestBody @Valid EventCardDTO event, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }

    eventService.editEvent(event, id);
    return ResponseEntity.ok(Map.of("message", "Event updated: " + event.getEventName()));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable int id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok().build();
  }
}
