package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventCardDTO;
import emv.backend.spring.fiesta.service.EventService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
public class ManageEventController {

  private final EventService eventService;

  public ManageEventController(EventService eventService, ModelMapper modelMapper) {
    this.eventService = eventService;
  }

  // TODO: reconsider to move endpoint
  @GetMapping("/events")
  public ResponseEntity<List<EventCardDTO>> getAllEvents() {
    return ResponseEntity.ok(eventService.getAllEventsSorted());
  }

  private ResponseEntity<Map<String, String>> handleErrorMessaging(BindingResult bindingResult) {
    Map<String, String> errors = new HashMap<>();
    for (ObjectError error : bindingResult.getAllErrors()) {
      String fieldName = ((FieldError) error).getField();
      String errorMsg = error.getDefaultMessage();
      errors.put(fieldName, errorMsg);
    }
    return ResponseEntity.badRequest().body(errors);
  }

  @PostMapping("/create")
  public ResponseEntity<Map<String, String>> createEvent(
      @RequestBody @Valid EventCardDTO event, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return handleErrorMessaging(bindingResult);
    }

    eventService.createEvent(event);
    return ResponseEntity.ok(Map.of("message", "Event created: " + event.getEventName()));
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<Map<String, String>> editEvent(
      @PathVariable int id, @RequestBody @Valid EventCardDTO event, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return handleErrorMessaging(bindingResult);
    }

    eventService.editEvent(event, id); // TODO: Handle Exception
    return ResponseEntity.ok(Map.of("message", "Event updated: " + event.getEventName()));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable int id) {
    eventService.deleteEvent(id);
    return ResponseEntity.ok().build();
  }
}
