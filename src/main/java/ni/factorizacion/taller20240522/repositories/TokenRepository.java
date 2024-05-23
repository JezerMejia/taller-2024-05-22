package ni.factorizacion.taller20240522.repositories;

import ni.factorizacion.taller20240522.domain.entities.Token;
import ni.factorizacion.taller20240522.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token> findByUserAndActive(User user, Boolean active);
}
