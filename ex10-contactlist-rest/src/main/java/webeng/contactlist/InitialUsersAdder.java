package webeng.contactlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import webeng.contactlist.model.User;
import webeng.contactlist.model.UserRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptySet;

@Component
public class InitialUsersAdder {

    private static final Logger logger = LoggerFactory.getLogger(InitialUsersAdder.class);

    private final UserRepository userRepo;
    private final Optional<String> adminPassword;

    public InitialUsersAdder(UserRepository userRepo,
                             @Value("${contact-list.admin-password:#{null}}") Optional<String> adminPassword) {
        this.userRepo = userRepo;
        this.adminPassword = adminPassword;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepo.findAll().isEmpty()) {
            var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            var adminPw = adminPassword.orElse(generatePassword());
            var admin = new User("admin", encoder.encode(adminPw), Set.of("ADMIN"));
            userRepo.save(admin);

            var userPw = generatePassword();
            var user = new User("user", encoder.encode(userPw), emptySet());
            userRepo.save(user);
            logger.info("Added sample users 'admin' and 'user'");

            // use sysout instead of logger, so passwords will not show up in log files
            System.out.println("\nPassword for admin: " + adminPw);
            System.out.println("Password for user: " + userPw + "\n");
        }
    }

    private String generatePassword() {
        var random = new SecureRandom();
        return new BigInteger(128, random).toString(32); // better than nextInt()...
    }
}
