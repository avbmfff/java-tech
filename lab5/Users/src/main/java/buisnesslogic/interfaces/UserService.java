package buisnesslogic.interfaces;

import buisnesslogic.repository.UserRepository;
import buisnesslogic.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static UserRepository userRepository;

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> findById(long id) { return userRepository.findById((double) id);
    }

    public static User saveUser(User user) {
        return userRepository.save(user);
    }

}
