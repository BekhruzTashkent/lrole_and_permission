package uz.pdp.lesson6_7role_and_permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.lesson6_7role_and_permission.entity.User;
import uz.pdp.lesson6_7role_and_permission.exceptions.ResourceNotFoundException;
import uz.pdp.lesson6_7role_and_permission.payload.ApiResponce;
import uz.pdp.lesson6_7role_and_permission.payload.RegisterDto;
import uz.pdp.lesson6_7role_and_permission.repository.LavozimRepository;
import uz.pdp.lesson6_7role_and_permission.repository.UserRepository;
import uz.pdp.lesson6_7role_and_permission.utilits.AppConstant;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponce registerUser(RegisterDto registerDto) {

        if(!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponce("password error", false);
        }

        if(userRepository.existsByUsername(registerDto.getUsername()))
        return new ApiResponce("username already exist", false);

      User user = new User(
              registerDto.getFullName(),
              registerDto.getUsername(),
              passwordEncoder.encode(registerDto.getPassword()),
              lavozimRepository.findByName(AppConstant.USER).orElseThrow(() -> new ResourceNotFoundException("lavozim","name",AppConstant.USER)),
              true
      );
      userRepository.save(user);
        return new ApiResponce("Successful authentication", true);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException(username));
    }
}
