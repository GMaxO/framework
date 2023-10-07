package org.example.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final Map<User, List<Authorities>> usersAuthoritiesList;

    private UserRepository() {
        usersAuthoritiesList = addUsersAutorities();
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> ans = new ArrayList<>();
        for (Map.Entry<User, List<Authorities>> pair : usersAuthoritiesList.entrySet()) {
            if (pair.getKey().getUser().equals(user) && pair.getKey().getPassword().equals(password))  {
                ans = pair.getValue();
            }
        }
        return ans;
    }

    private Map<User, List<Authorities>> addUsersAutorities() {
        Map<User, List<Authorities>> usersAuthoritiesList = new HashMap<>();
        List<Authorities> opt1 = new ArrayList<>();
        opt1.add(Authorities.READ);
        opt1.add(Authorities.WRITE);
        opt1.add(Authorities.DELETE);
        List<Authorities> opt2 = new ArrayList<>();
        opt2.add(Authorities.READ);
        opt2.add(Authorities.WRITE);
        List<Authorities> opt3 = new ArrayList<>();
        opt3.add(Authorities.READ);
        User user1 = new User("igor", "123");
        User user2 = new User("oleg", "123456");
        User user3 = new User("adelya", "123456789");

        usersAuthoritiesList.put(user3, opt3);
        usersAuthoritiesList.put(user1, opt1);
        usersAuthoritiesList.put(user2, opt2);
        return usersAuthoritiesList;
    }
}
