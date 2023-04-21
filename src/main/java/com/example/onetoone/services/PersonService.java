package com.example.onetoone.services;

import com.example.onetoone.models.Person;
import com.example.onetoone.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Данный метод позволяет получить список всех пользователей
    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    // Данный метод позволяет добавить нового пользователя
    @Transactional
    public void personAdd(Person person){
        personRepository.save(person);
    }




}
