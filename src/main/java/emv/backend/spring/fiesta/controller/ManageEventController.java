package emv.backend.spring.fiesta.controller;

import emv.backend.spring.fiesta.dto.EventDTO;
import emv.backend.spring.fiesta.dto.EventManageDTO;
import emv.backend.spring.fiesta.model.eventSchema.Event;
import emv.backend.spring.fiesta.security.AppUserDetails;
import emv.backend.spring.fiesta.service.eventSchema.EventService;
import emv.backend.spring.fiesta.util.FailedValidationResponseHandler;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class ManageEventController {

  private final EventService eventService;
  private final ModelMapper modelMapper;

  public ManageEventController(EventService eventService, ModelMapper modelMapper) {
    this.eventService = eventService;
    this.modelMapper = modelMapper;
  }

  @PostConstruct
  public void init() {
    modelMapper
        .typeMap(Event.class, EventDTO.class)
        .addMapping(
            src -> src.getHost().getHostName(), (dest, value) -> dest.setHostName((String) value));
  }

  @GetMapping
  public List<EventDTO> getEventsByHost(Authentication auth) {
    Integer authenticatedUserId = ((AppUserDetails) auth.getPrincipal()).getId();

    return eventService.getEventsByUserId(authenticatedUserId).stream()
        .map(entity -> modelMapper.map(entity, EventDTO.class))
        .toList();
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, String> createEvent(
      @RequestBody @Valid EventManageDTO event, BindingResult bindingResult, Authentication auth) {

    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }

    Integer authenticatedUserId = ((AppUserDetails) auth.getPrincipal()).getId();

    Event entity = modelMapper.map(event, Event.class);
    eventService.createEvent(entity, authenticatedUserId);
    return Map.of("message", "Event created: " + event.getEventName());
  }

  @PutMapping("/edit/{id}")
  public Map<String, String> editEvent(
      @PathVariable int id,
      @RequestBody @Valid EventManageDTO event,
      BindingResult bindingResult,
      Authentication auth) {
    if (bindingResult.hasErrors()) {
      return FailedValidationResponseHandler.handleErrorMessaging(bindingResult);
    }

    Integer authenticatedUserId = ((AppUserDetails) auth.getPrincipal()).getId();

    Event entity = modelMapper.map(event, Event.class);

    eventService.editEvent(entity, id, authenticatedUserId);
    return Map.of("message", "Event updated: " + event.getEventName());
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEvent(@PathVariable int id, Authentication auth) {
    Integer authenticatedUserId = ((AppUserDetails) auth.getPrincipal()).getId();

    eventService.deleteEvent(id, authenticatedUserId);
    return ResponseEntity.ok().build();
  }
}
