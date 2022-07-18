package uz.pdp.lesson6_7role_and_permission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.lesson6_7role_and_permission.entity.User;
import uz.pdp.lesson6_7role_and_permission.payload.ApiResponce;
import uz.pdp.lesson6_7role_and_permission.payload.LoginDto;
import uz.pdp.lesson6_7role_and_permission.payload.RegisterDto;
import uz.pdp.lesson6_7role_and_permission.secure.JwtProvider;
import uz.pdp.lesson6_7role_and_permission.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {  //this class for user registration

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
       ApiResponce apiResponce = authService.registerUser(registerDto);
       return ResponseEntity.status(apiResponce.isSucces() ? 200 : 409 ).body(apiResponce);
    }

    @PostMapping("/login")
    public HttpEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = (User)authentication.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getLavozim()); //it gives us token
        return ResponseEntity.ok(token);  //we return it to token
    }
    

}
