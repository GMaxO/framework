package ru.netology.netologyspringboottask4.service;

import org.springframework.stereotype.Service;
import ru.netology.netologyspringboottask4.exception.InvalidCredentials;
import ru.netology.netologyspringboottask4.exception.UnauthorizedUser;
import ru.netology.netologyspringboottask4.model.Authorities;
import ru.netology.netologyspringboottask4.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {

    UserRepository userRepository;

    private AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("USER NAME OR PASSWORD IS EMPTY");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("UNKNOWN USER " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
