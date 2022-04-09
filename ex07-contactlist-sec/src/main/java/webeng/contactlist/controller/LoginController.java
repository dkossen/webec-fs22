package webeng.contactlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import webeng.contactlist.service.ContactService;

@Controller
@Validated
public class LoginController {

    private final ContactService service;

    public LoginController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
