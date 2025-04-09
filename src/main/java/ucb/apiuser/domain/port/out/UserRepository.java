package ucb.apiuser.domain.port.out;

import java.util.List;
import java.util.Optional;
import ucb.apiuser.domain.model.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void delete(Long id);
    boolean existsById(Long id);
}
