package ni.factorizacion.taller20240522.controllers;

import lombok.RequiredArgsConstructor;
import ni.factorizacion.taller20240522.domain.dtos.GeneralResponse;
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
//    private final UserService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<String>>> getAllUsers() {
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found users", List.of(""));
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<String>> getUser(@PathVariable String identifier) {
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found", "awa");
    }
}
