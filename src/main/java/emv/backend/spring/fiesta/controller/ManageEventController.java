package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.model.Event;
import emv.backend.spring.fiesta.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageEventController {

  private final EventService eventService;

  public ManageEventController(EventService eventService) {
    this.eventService = eventService;
  }

  // TODO: create DTO object
  // TODO: reconsider to move endpoint
  @GetMapping("/events")
  public ResponseEntity<List<Event>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEvents());
  }

  @PostMapping("/create")
  public ResponseEntity<Map<String, String>> acceptEventInformation(
      @RequestBody @Valid Event event, BindingResult bindingResult) {

    Map<String, String> errors = new HashMap<>();

    if (bindingResult.hasErrors()) {
      for (ObjectError error : bindingResult.getAllErrors()) {
        String fieldName = ((FieldError) error).getField();
        String errorMsg = error.getDefaultMessage();
        errors.put(fieldName, errorMsg);
      }

      return ResponseEntity.badRequest().body(errors);
    }
    eventService.saveEventDetails(event);
    return ResponseEntity.ok(Map.of("message", "Event created: " + event.getEventName()));
  }
}
