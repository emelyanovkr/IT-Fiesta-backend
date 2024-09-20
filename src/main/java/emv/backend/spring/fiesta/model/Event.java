package emv.backend.spring.fiesta.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_data")
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 255, message = "Too big name's size")
  @NotBlank(message = "Name can't be empty")
  @NotNull
  @Column(name = "event_name", nullable = false)
  private String eventName;

  @Size(max = 255, message = "Too big name's size")
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
  @Column(name = "description")
  private String description;

  @Column(name = "additional_info")
  private String additionalInfo;

  public Event() {}

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getMaxVisitors() {
    return maxVisitors;
  }

  public void setMaxVisitors(Integer maxVisitors) {
    this.maxVisitors = maxVisitors;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalDateTime getDateOfEvent() {
    return dateOfEvent;
  }

  public void setDateOfEvent(LocalDateTime dateOfEvent) {
    this.dateOfEvent = dateOfEvent;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
