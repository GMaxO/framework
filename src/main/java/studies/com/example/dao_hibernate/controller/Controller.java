package com.example.dao_hibernate.controller;

import com.example.dao_hibernate.repository.RepositoryPerson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private final RepositoryPerson repositoryPerson;

    public Controller(RepositoryPerson repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }

    @GetMapping("/products/fetch-product")
    @ResponseBody
    public List getUserName(@RequestParam("city") String city) {

        return repositoryPerson.getPersonsByCity(city);
    }

}
