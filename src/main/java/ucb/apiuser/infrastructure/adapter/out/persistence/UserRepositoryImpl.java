package ucb.apiuser.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;
import ucb.apiuser.domain.model.User;
import ucb.apiuser.domain.port.out.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    
    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    
    @Override
    public User save(User user) {
        UserEntity userEntity = mapToEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return mapToDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(this::mapToDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }
    
    private UserEntity mapToEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getBornDate()
        );
    }
    
    private User mapToDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastname(),
                userEntity.getPhoneNumber(),
                userEntity.getBornDate()
        );
    }
}
