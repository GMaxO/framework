package api.dto.auth_dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterUserDTO extends UserDTO {
    private String partner;

    public RegisterUserDTO(String channel, String password, String partner) {
        super(channel, password);
        this.partner = partner;
    }
}
