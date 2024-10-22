package emv.backend.spring.fiesta.model.eventSchema;

import emv.backend.spring.fiesta.model.userSchema.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(schema = "event_data_schema", name = "hosts")
public class Host {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private AppUser appUser;

  @Size(max = 255, message = "Host name is too big, please limit to 255")
  @Column(name = "host_name", unique = true, nullable = false)
  private String hostName;

  @OneToMany(mappedBy = "host")
  private List<Event> hostedEvents;

  public Host() {}

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getHostName() {
    return hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public List<Event> getHostedEvents() {
    return hostedEvents;
  }

  public void setHostedEvents(List<Event> hostedEvents) {
    this.hostedEvents = hostedEvents;
  }

  public AppUser getAppUser() {
    return appUser;
  }

  public void setAppUser(AppUser appUser) {
    this.appUser = appUser;
  }
}
