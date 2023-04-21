package com.example.onetoone.repositories;

import com.example.onetoone.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    // Данный метод позволяет получить пользователя по фамилии
    Person findByLastName(String lastName);
}
