package ni.factorizacion.taller20240522.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.taller20240522.domain.dtos.GeneralResponse;
import ni.factorizacion.taller20240522.domain.dtos.SaveUserDto;
import ni.factorizacion.taller20240522.domain.entities.User;
import ni.factorizacion.taller20240522.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (user == null || !user.getActive()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.OK, "Found user", user);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> saveUser(@RequestBody @Valid SaveUserDto user) {
        service.saveUser(user);

        return GeneralResponse.getResponse(HttpStatus.OK, "User saved", null);
    }

    @PatchMapping(value = "/toggle/{identifier}")
    public ResponseEntity<GeneralResponse<String>> toggleUser(@PathVariable String identifier) {
        User user = service.findByUsername(identifier);
        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found", null);
        }
        service.toggleActive(user);

        return GeneralResponse.getResponse(HttpStatus.OK, "Toggled user", null);
    }

}
