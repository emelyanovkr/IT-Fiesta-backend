package emv.backend.spring.fiesta.repository;

import emv.backend.spring.fiesta.model.userAccount.Role;
import emv.backend.spring.fiesta.model.userAccount.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(RoleType name);
}
