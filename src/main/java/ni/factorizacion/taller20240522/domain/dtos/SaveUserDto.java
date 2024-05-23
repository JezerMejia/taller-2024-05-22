package ni.factorizacion.taller20240522.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SaveUserDto {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String username;

    @NotEmpty
    @Pattern(regexp = "^\\d*\\w$")
    private String password;

    @NotEmpty
    private String email;
}
