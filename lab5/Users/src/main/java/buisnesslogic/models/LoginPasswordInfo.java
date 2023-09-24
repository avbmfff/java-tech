package buisnesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginPasswordInfo {
    private String loginPassword;
    private Boolean isValid;

}
