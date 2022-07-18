package uz.pdp.lesson6_7role_and_permission.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.lesson6_7role_and_permission.entity.Lavozim;
import uz.pdp.lesson6_7role_and_permission.entity.User;
import uz.pdp.lesson6_7role_and_permission.entity.enums.Huquq;
import uz.pdp.lesson6_7role_and_permission.repository.LavozimRepository;
import uz.pdp.lesson6_7role_and_permission.repository.UserRepository;
import uz.pdp.lesson6_7role_and_permission.utilits.AppConstant;

import java.util.Arrays;

import static uz.pdp.lesson6_7role_and_permission.entity.enums.Huquq.*;

public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")  //spring.sql.init.mode from applications.properties, we need this when we put always
    private String initialMode;

    @Override
    public void run(String... args) {                //method of CommandLineRunner
        if (initialMode.equals("always")) {
            Huquq[] huquqsList = Huquq.values();            //enums give us lists in .values

            Lavozim admin = lavozimRepository.save(new Lavozim(
                    AppConstant.ADMIN,
                    Arrays.asList(huquqsList),     //list of admin huquqs
                    "sistema egasi"
            ));

            Lavozim user = lavozimRepository.save(new Lavozim(
                    AppConstant.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),     //list of user huquqs
                    "sistema egasi"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),  //we encode password
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"), //we encode paswword
                    user,
                    true
            ));
        }
    }
}
