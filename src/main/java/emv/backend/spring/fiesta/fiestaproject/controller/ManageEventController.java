package emv.backend.spring.fiesta.fiestaproject.controller;

import emv.backend.spring.fiesta.fiestaproject.model.Event;
import emv.backend.spring.fiesta.fiestaproject.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageEventController {

  private final EventService eventService;

  public ManageEventController(EventService eventService) {
    this.eventService = eventService;
  }
}
