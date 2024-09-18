package emv.backend.spring.fiesta.fiestaproject.model;

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
  @Column(name = "name", nullable = false)
  private String name;

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

  @Max(value = 10000, message = "Too many max visitors, attend below 10000, please")
  @Column(name = "max_visitors")
  private Integer maxVisitors;

  @NotBlank(message = "Please, specify description of your event")
  @NotNull
  @Column(name = "description", length = Integer.MAX_VALUE)
  private String description;

  @Column(name = "additional_info", length = Integer.MAX_VALUE)
  private String additionalInfo;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
