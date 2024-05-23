package ni.factorizacion.taller20240522.domain.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
