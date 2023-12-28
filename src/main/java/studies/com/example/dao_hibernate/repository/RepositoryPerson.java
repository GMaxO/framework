package com.example.dao_hibernate.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Repository
public class RepositoryPerson {
    @PersistenceContext
    private EntityManager entityManager;

    public List getPersonsByCity(String city) {

        List result = entityManager.createNativeQuery("SELECT * FROM persons WHERE city_of_living ILIKE :city").setParameter("city", city).getResultList();

        System.out.println(Arrays.deepToString(result.toArray()));
        return result;
    }
}
