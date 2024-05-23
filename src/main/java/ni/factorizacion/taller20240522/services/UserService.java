package ni.factorizacion.taller20240522.services;

import ni.factorizacion.taller20240522.domain.dtos.SaveUserDto;
import ni.factorizacion.taller20240522.domain.entities.Token;
import ni.factorizacion.taller20240522.domain.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void saveUser(SaveUserDto userDto);

    void toggleActive(User user);

    User findByUsername(String username);

    Boolean validAuthentication(User user, String password);

    User findUserAuthenticated();

    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
}
