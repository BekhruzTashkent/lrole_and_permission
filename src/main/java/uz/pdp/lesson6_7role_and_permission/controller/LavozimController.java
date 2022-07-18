package uz.pdp.lesson6_7role_and_permission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson6_7role_and_permission.aop.HuquqniTekshirish;
import uz.pdp.lesson6_7role_and_permission.payload.ApiResponce;
import uz.pdp.lesson6_7role_and_permission.payload.LavozimDto;
import uz.pdp.lesson6_7role_and_permission.payload.UserDto;
import uz.pdp.lesson6_7role_and_permission.service.LavozimService;
import uz.pdp.lesson6_7role_and_permission.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {  //this class for user registration

    @Autowired
    LavozimService lavozimService;

    @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")
    @PostMapping("/register")
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponce apiResponce = lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponce.isSucces() ? 200 : 409 ).body(apiResponce);
    }


    @PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
    @HuquqniTekshirish(huquq = "EDIT_LAVOZIM")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(@PathVariable long id,
            @Valid @RequestBody LavozimDto lavozimDto){
        ApiResponce apiResponce = lavozimService.editLavozim(id, lavozimDto);
        return ResponseEntity.status(apiResponce.isSucces() ? 200 : 409 ).body(apiResponce);
    }
}
