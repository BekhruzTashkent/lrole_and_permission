package uz.pdp.lesson6_7role_and_permission.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiResponce {

    private String message;
    private boolean succes;

}
