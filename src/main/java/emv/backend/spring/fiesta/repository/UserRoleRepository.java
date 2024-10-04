package emv.backend.spring.fiesta.repository;

import emv.backend.spring.fiesta.model.userAccount.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {}
