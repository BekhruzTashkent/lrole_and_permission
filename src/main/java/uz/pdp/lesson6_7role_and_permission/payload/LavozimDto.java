package uz.pdp.lesson6_7role_and_permission.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson6_7role_and_permission.entity.enums.Huquq;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LavozimDto {


    private String name;

    private String description;

    private List<Huquq> huquqList;
}
