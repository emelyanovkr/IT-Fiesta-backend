package emv.backend.spring.fiesta.repository;

import emv.backend.spring.fiesta.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

  Optional<AppUser> findByUsername(String username);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
