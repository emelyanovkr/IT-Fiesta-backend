package emv.backend.spring.fiesta.repository.userSchema;

import emv.backend.spring.fiesta.model.userSchema.Role;
import emv.backend.spring.fiesta.model.userSchema.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(RoleType name);
}
