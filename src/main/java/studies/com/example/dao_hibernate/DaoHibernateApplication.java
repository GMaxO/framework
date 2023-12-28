package com.example.dao_hibernate;

import com.example.dao_hibernate.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@SpringBootApplication
public class DaoHibernateApplication implements CommandLineRunner {
    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(DaoHibernateApplication.class, args);

    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {

        Person tom = new Person();
        tom.setName("Tom");
        tom.setSurname("Tomer");
        tom.setAge(44);
        tom.setCity_of_living("Kostroma");
        tom.setPhone_number("1234");
        entityManager.persist(tom);

        Person ivan = new Person();
        ivan.setName("Ivan");
        ivan.setSurname("Ivanov");
        ivan.setAge(34);
        ivan.setCity_of_living("Buy");
        ivan.setPhone_number("1222");
        entityManager.persist(ivan);

        Person fedr = new Person();
        fedr.setName("Fedr");
        fedr.setSurname("Fedorov");
        fedr.setAge(64);
        fedr.setCity_of_living("Susanino");
        fedr.setPhone_number("3333");
        entityManager.persist(fedr);

        Person roma = new Person();
        roma.setName("Roma");
        roma.setSurname("Romov");
        roma.setAge(18);
        roma.setCity_of_living("Kostroma");
        roma.setPhone_number("8888");
        entityManager.persist(roma);

    }
}
