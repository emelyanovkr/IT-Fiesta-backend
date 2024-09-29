package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.service.EventService;
import emv.backend.spring.fiesta.util.ErrorMessagesResponseHandler;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageEventController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ManageEventController.class);

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
      return ErrorMessagesResponseHandler.handleErrorMessaging(bindingResult);
    }

    eventService.createEvent(event);
    return ResponseEntity.ok(Map.of("message", "Event created: " + event.getEventName()));
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<Map<String, String>> editEvent(
      @PathVariable int id, @RequestBody @Valid EventCardDTO event, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return ErrorMessagesResponseHandler.handleErrorMessaging(bindingResult);
    }

    try {
      eventService.editEvent(event, id);
    } catch (EntityNotFoundException e) {
      LOGGER.error(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(Map.of("message", "Event not found: " + event.getEventName()));
    }
    return ResponseEntity.ok(Map.of("message", "Event updated: " + event.getEventName()));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable int id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok().build();
  }
}
