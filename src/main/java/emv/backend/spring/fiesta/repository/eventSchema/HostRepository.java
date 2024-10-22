package emv.backend.spring.fiesta.repository.eventSchema;

import emv.backend.spring.fiesta.model.eventSchema.Host;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HostRepository extends JpaRepository<Host, Integer> {

  @Query("SELECT id FROM Host WHERE appUser.id = :userId")
  Optional<Integer> findHostIdByUserId(@Param("userId") int id);
}
