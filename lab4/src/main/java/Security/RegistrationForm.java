package Security;

import Entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private List<String> roles;
    private String email;
    private String firstName;
    private String lastName;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                roles, email, firstName, lastName);
    }
}
