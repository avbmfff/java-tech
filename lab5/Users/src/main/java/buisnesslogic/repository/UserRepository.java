package buisnesslogic.repository;

import buisnesslogic.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Double> {
    User findByUsername(Object username);
    void saveUser(User user);
}