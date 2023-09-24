package buisnesslogic.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NotNull
@AllArgsConstructor
@Getter
@Setter
public class Role {

    private Long id;

    private String name;

    private Set<User> users = new HashSet<>();

}