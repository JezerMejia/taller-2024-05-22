package ni.factorizacion.taller20240522.services;

import ni.factorizacion.taller20240522.domain.entities.Token;
import ni.factorizacion.taller20240522.domain.entities.User;

public interface UserService {

    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
}
