package ni.factorizacion.taller20240522.controllers;

import lombok.RequiredArgsConstructor;
import ni.factorizacion.taller20240522.domain.dtos.GeneralResponse;
import ni.factorizacion.taller20240522.domain.entities.User;
import ni.factorizacion.taller20240522.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users/", produces = "application/json")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<User>>> getAllUsers() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NO_CONTENT, "No users found", users);
        }
        return GeneralResponse.getResponse(HttpStatus.OK, "Found users", users);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<User>> getUser(@PathVariable String identifier) {
        User user = service.findByUsername(identifier);
        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.OK, "Found user", user);
    }

}
