package uz.pdp.lesson6_7role_and_permission.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {


    @NotNull(message = "there should not be empty fullName")   //get dependency from validation
    private String fullName;

    @NotNull(message = "there should not be empty fullName")   //get dependency from validation
    private String username;

    @NotNull(message = "there should not be empty password")
    private String password;

    @NotNull(message = "there should not be empty password")
    private Integer lavozimId;

}
