package ni.factorizacion.taller20240522.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.taller20240522.domain.dtos.GeneralResponse;
import ni.factorizacion.taller20240522.domain.dtos.TokenDTO;
import ni.factorizacion.taller20240522.domain.dtos.UserLoginDto;
import ni.factorizacion.taller20240522.domain.entities.Token;
import ni.factorizacion.taller20240522.domain.entities.User;
import ni.factorizacion.taller20240522.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users/", produces = "application/json")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<String>>> getAllUsers() {
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found users", List.of(""));
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<String>> getUser(@PathVariable String identifier) {
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found", "awa");
    }

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<TokenDTO>> login(@ModelAttribute @Valid UserLoginDto info, BindingResult validations){
        User user = service.findByUsername(info.getUsername());

        if (!service.validAuthentication(user, info.getPassword())) {
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Invalid authentication", null);
        }

        try {
            Token token = service.registerToken(user);
            return GeneralResponse.getResponse(HttpStatus.OK, "", new TokenDTO(token));
        } catch (Exception e) {
            e.printStackTrace();
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null);
        }
    }
}
