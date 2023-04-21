package com.example.onetoone.controllers;

import com.example.onetoone.models.Passport;
import com.example.onetoone.models.Person;
import com.example.onetoone.repositories.PassportRepository;
import com.example.onetoone.services.PassportService;
import com.example.onetoone.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final PassportService passportService;
    private final PersonService personService;

    private final PassportRepository passportRepository;

    @Autowired
    public MainController(PassportService passportService, PersonService personService, PassportRepository passportRepository) {
        this.passportService = passportService;
        this.personService = personService;
        this.passportRepository = passportRepository;
    }

    // Данный метод позволяет получить список всех пользователей и передать их в представление
    @GetMapping("/person")
    public String getAllPerson(Model model){
        model.addAttribute("persons", personService.getAllPerson());
        return "person";
    }

    // Данный метод позволяет получить список всех пользователей и передать их в представление
    @GetMapping("/passport")
    public String getAllPassport(Model model){
        model.addAttribute("passports", passportService.getAllPassport());
        return "passport";
    }

    // Данный метод позволяет вернуть представление с добавление пользователей и передать список всех паспортов
    @GetMapping("/person/new")
    public String personAdd(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("passports", passportService.getAllPassport());
        return "personAdd";
    }

    @PostMapping("/person/new")
    public String personAdd(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @RequestParam String number, Model model){
        System.out.println(person.getLastName());
        Passport passport = passportRepository.findByNumber(number);
        Person newPerson = new Person(person.getLastName(), person.getFirstName(), person.getPatronymic(), passport);
        for (Person person_db: personService.getAllPerson()) {
            if (person_db.getPassport().getId() == newPerson.getPassport().getId())
            {
                model.addAttribute("person", person);
                model.addAttribute("passports", passportService.getAllPassport());
//                model.addAttribute("error", "Пользователь с таким паспортом уже существует");
                ObjectError error = new ObjectError("error", "Пользователь с таким паспортом уже существует");
                bindingResult.addError(error);
                return "personAdd";
            }
        }
        personService.personAdd(newPerson);
        return "redirect:/person";
    }
}
