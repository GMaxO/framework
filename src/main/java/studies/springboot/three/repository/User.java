package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public class User {
    private String user;
    private String password;


    public User() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public User(String user, String password) {
        this.user = user;
        this.password = password;
//        this.userAuthorities = userAuthorities;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
