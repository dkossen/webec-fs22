package ch.fhnw.webeng.contactlist.controller;

import ch.fhnw.webeng.contactlist.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class AboutController {

    private final ContactService service;

    public AboutController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    private static class AboutNotFound extends RuntimeException {}
}
