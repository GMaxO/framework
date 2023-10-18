package ru.netology.netologyspringboottask4.repository;

import org.springframework.stereotype.Repository;
import ru.netology.netologyspringboottask4.model.Authorities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>();
        if (user.equals("Vasya") && password.equals("1234")) {
            Collections.addAll(authorities, Authorities.READ, Authorities.WRITE, Authorities.DELETE);
        } else if (user.equals("Olya") && password.equals("2345")) {
            Collections.addAll(authorities, Authorities.READ, Authorities.WRITE);
        } else if (user.equals("Mariy") && password.equals("3456")) {
            Collections.addAll(authorities, Authorities.READ);
        }
        return authorities;
    }
}
