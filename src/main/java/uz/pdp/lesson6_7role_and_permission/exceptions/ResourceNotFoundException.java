package uz.pdp.lesson6_7role_and_permission.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName;  //lavozim  not found
    private final String resourceField;  //name   not found
    private final Object object;   //USER, 1, 500  not found

}
