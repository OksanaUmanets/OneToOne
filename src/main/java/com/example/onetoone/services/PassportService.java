package com.example.onetoone.services;

import com.example.onetoone.models.Passport;
import com.example.onetoone.models.Person;
import com.example.onetoone.repositories.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PassportService {

    private final PassportRepository passportRepository;


    @Autowired
    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    // Данный метод позволяет получить список всех паспортов
    public List<Passport> getAllPassport(){
        return passportRepository.findAll();
    }


    // Данный метод позволяет добавить новый паспорт
    @Transactional
    public void passportAdd(Passport passport){
        passportRepository.save(passport);
    }

}
