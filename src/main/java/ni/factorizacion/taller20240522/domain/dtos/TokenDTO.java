package ni.factorizacion.taller20240522.domain.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ni.factorizacion.taller20240522.domain.entities.Token;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String token;

    public TokenDTO(Token token) {
        this.token = token.getContent();
    }

}
