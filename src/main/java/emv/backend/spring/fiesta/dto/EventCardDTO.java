package emv.backend.spring.fiesta.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class EventCardDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 255, message = "Too big name's size")
  @NotBlank(message = "Name can't be empty")
  @NotNull
  @Column(name = "event_name", nullable = false)
  private String eventName;

  @Size(max = 150, message = "Too big hostName's size")
  @NotBlank(message = "Hostname can't be empty")
  @NotNull
  @Column(name = "host_name", nullable = false)
  private String hostName;

  @NotNull
  @Column(name = "date_of_event", nullable = false)
  private LocalDateTime dateOfEvent;

  @Size(max = 350, message = "Location name is too long")
  @NotBlank(message = "Please, specify location for your event")
  @NotNull
  @Column(name = "location", nullable = false)
  private String location;

  @Max(value = 1000, message = "Too many max visitors, attend below 1000, please")
  @Column(name = "max_visitors")
  private Integer maxVisitors;

  @NotBlank(message = "Please, specify description of your event")
  @NotNull
  @Size(max = 1000, message = "Too large description information")
  @Column(name = "description")
  private String description;

  public EventCardDTO() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public LocalDateTime getDateOfEvent() {
    return dateOfEvent;
  }

  public void setDateOfEvent(LocalDateTime dateOfEvent) {
    this.dateOfEvent = dateOfEvent;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getMaxVisitors() {
    return maxVisitors;
  }

  public void setMaxVisitors(Integer maxVisitors) {
    this.maxVisitors = maxVisitors;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
