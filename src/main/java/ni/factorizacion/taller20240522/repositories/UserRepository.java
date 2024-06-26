package ni.factorizacion.taller20240522.repositories;

import ni.factorizacion.taller20240522.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    List<User> findAllByActive(boolean active);
}
