package emv.backend.spring.fiesta.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class EventManageDTO {
  private Integer id;

  @Size(max = 255, message = "Too big name's size")
  @NotBlank(message = "Name can't be empty")
  @NotNull
  private String eventName;

  @NotNull private LocalDateTime dateOfEvent;

  @Size(max = 350, message = "Location name is too long")
  @NotBlank(message = "Please, specify location for your event")
  @NotNull
  private String location;

  @Max(value = 1000, message = "Too many max visitors, attend below 1000, please")
  private Integer maxVisitors;

  @NotBlank(message = "Please, specify description of your event")
  @NotNull
  @Size(max = 1000, message = "Too large description information")
  private String description;

  public EventManageDTO() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public @Size(max = 255, message = "Too big name's size") @NotBlank(
      message = "Name can't be empty") @NotNull String getEventName() {
    return eventName;
  }

  public void setEventName(
      @Size(max = 255, message = "Too big name's size")
          @NotBlank(message = "Name can't be empty")
          @NotNull
          String eventName) {
    this.eventName = eventName;
  }

  public @NotNull LocalDateTime getDateOfEvent() {
    return dateOfEvent;
  }

  public void setDateOfEvent(@NotNull LocalDateTime dateOfEvent) {
    this.dateOfEvent = dateOfEvent;
  }

  public @Size(max = 350, message = "Location name is too long") @NotBlank(
      message = "Please, specify location for your event") @NotNull String getLocation() {
    return location;
  }

  public void setLocation(
      @Size(max = 350, message = "Location name is too long")
          @NotBlank(message = "Please, specify location for your event")
          @NotNull
          String location) {
    this.location = location;
  }

  public @Max(value = 1000, message = "Too many max visitors, attend below 1000, please") Integer
      getMaxVisitors() {
    return maxVisitors;
  }

  public void setMaxVisitors(
      @Max(value = 1000, message = "Too many max visitors, attend below 1000, please")
          Integer maxVisitors) {
    this.maxVisitors = maxVisitors;
  }

  public @NotBlank(message = "Please, specify description of your event") @NotNull @Size(
      max = 1000,
      message = "Too large description information") String getDescription() {
    return description;
  }

  public void setDescription(
      @NotBlank(message = "Please, specify description of your event")
          @NotNull
          @Size(max = 1000, message = "Too large description information")
          String description) {
    this.description = description;
  }
}
