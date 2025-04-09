package ucb.apiuser.domain.port.in;

import java.util.List;
import ucb.apiuser.domain.model.User;

public interface UserUseCase {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
