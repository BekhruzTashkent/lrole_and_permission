package uz.pdp.lesson6_7role_and_permission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.lesson6_7role_and_permission.entity.enums.Huquq;
import uz.pdp.lesson6_7role_and_permission.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Lavozim extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String name;    //admin, user and others

    @Enumerated(value = EnumType.STRING)  //to show huquq_list as String in db
    @ElementCollection(fetch= FetchType.LAZY)  //because it is enum class
    private List<Huquq> huquqList;        //instead of Huquq we cen write String, Integer etc

  //  @Enumerated(value = EnumType.STRING)   //because it is enum class
  //  private LavozimTurlari lavozimTurlari;

    @Column(length = 500)
    private String description;

}
