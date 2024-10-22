package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventDTO;
import emv.backend.spring.fiesta.security.AppUserDetails;
import emv.backend.spring.fiesta.service.eventSchema.EventService;
import emv.backend.spring.fiesta.util.FailedValidationResponseHandler;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManageEventController {

  private final EventService eventService;

  public ManageEventController(EventService eventService) {
    this.eventService = eventService;
  }

  // TODO: redo all operations for specified host
  @GetMapping
  public List<EventDTO> getEventsByHost(Authentication auth) {
    Integer authenticatedUserId = ((AppUserDetails) auth.getPrincipal()).getId();

    return eventService.getEventsByUserId(authenticatedUserId);
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> createEvent(
      @RequestBody @Valid EventDTO event, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }

    eventService.createEvent(event);
    return Map.of("message", "Event created: " + event.getEventName());
  }

  @PutMapping("/edit/{id}")
  public Map<String, String> editEvent(
      @PathVariable int id, @RequestBody @Valid EventDTO event, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }

    eventService.editEvent(event, id);
    return Map.of("message", "Event updated: " + event.getEventName());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable int id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok().build();
  }
}
