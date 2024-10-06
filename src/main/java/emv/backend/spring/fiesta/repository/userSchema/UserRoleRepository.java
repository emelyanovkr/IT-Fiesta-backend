package emv.backend.spring.fiesta.repository.userSchema;

import emv.backend.spring.fiesta.model.userSchema.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {}
