package ucb.apiuser.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ucb.apiuser.domain.port.in.UserUseCase;
import ucb.apiuser.domain.port.out.UserRepository;
import ucb.apiuser.application.service.UserService;

@Configuration
public class BeanConfiguration {
    
    @Bean
    public UserUseCase userUseCase(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
