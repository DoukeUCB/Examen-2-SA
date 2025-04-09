package ucb.apiuser.application.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ucb.apiuser.domain.model.User;
import ucb.apiuser.domain.port.in.UserUseCase;
import ucb.apiuser.domain.port.out.UserRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService implements UserUseCase {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario con id " + id + " no encontrado"));
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario con id " + id + " no encontrado");
        }
        user.setId(id);
        return userRepository.save(user);
    }
    
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario con id " + id + " no encontrado");
        }
        userRepository.delete(id);
    }
}
