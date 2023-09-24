package buisnesslogic.rest.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;
import javax.validation.ValidationException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    private Double id;
    private String username;
    private String password;
    private List<String> roles;

    @JsonbCreator
    public CreateUserRequest(@JsonbProperty("id") Double id, @JsonbProperty("username") String username,
                             @JsonbProperty("password") String password, @JsonbProperty("roles") List<String> roles){
        this.id = id;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public void validate(){
        if(this.id == null){
            throw new ValidationException("id is required");
        }

        if(this.username == null){
            throw new ValidationException("username is required");
        }

        if (this.password == null){
            throw new ValidationException("password is required");
        }

        if (this.roles == null){
            throw new ValidationException("roles is required");
        }

    }
}
