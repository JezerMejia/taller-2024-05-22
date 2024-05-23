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

@RestController
@RequestMapping(value = "/auth/", consumes = "application/json")
@RequiredArgsConstructor
public class AuthRestController {
    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<TokenDTO>> login(@Valid @RequestBody UserLoginDto info, BindingResult validations) {
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
