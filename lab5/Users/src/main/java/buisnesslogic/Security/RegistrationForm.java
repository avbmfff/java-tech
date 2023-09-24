package buisnesslogic.Security;

import buisnesslogic.models.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Data
public class RegistrationForm {
    private Double id;
    private String username;
    private String password;
    private List<String> roles;
    private String email;
    private String firstName;
    private String lastName;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                id, username, passwordEncoder.encode(password),
                roles);
    }
}
