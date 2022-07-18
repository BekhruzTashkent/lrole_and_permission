package uz.pdp.lesson6_7role_and_permission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lesson6_7role_and_permission.payload.ApiResponce;
import uz.pdp.lesson6_7role_and_permission.payload.RegisterDto;
import uz.pdp.lesson6_7role_and_permission.payload.UserDto;
import uz.pdp.lesson6_7role_and_permission.service.AuthService;
import uz.pdp.lesson6_7role_and_permission.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {  //this class for user registration

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
       ApiResponce apiResponce = userService.addUser(userDto);
       return ResponseEntity.status(apiResponce.isSucces() ? 200 : 409 ).body(apiResponce);
    }

    

}
