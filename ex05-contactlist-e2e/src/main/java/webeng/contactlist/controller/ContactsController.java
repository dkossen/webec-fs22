package webeng.contactlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import webeng.contactlist.service.ContactService;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class ContactsController {

    private final ContactService service;

    public ContactsController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/contacts")
    public String contacts(Model model, @RequestParam(required = false) String search) {
        model.addAttribute("contactList", service.getContactList());
        model.addAttribute("searchResult", service.searchContacts("a").size());
        return "contacts";
    }

    @GetMapping("/contacts/{id}")
    public String showContact(@PathVariable int id, Model model) {
        var contact = service.findContact(id).orElseThrow(ContactNotFound::new);
        model.addAttribute("contactList", service.getContactList());
        model.addAttribute("contact", contact);
        return "contacts";
    }

    @ExceptionHandler(ContactNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("contactList", service.getContactList());
        return "contacts";
    }

    private static class ContactNotFound extends RuntimeException {}
}
