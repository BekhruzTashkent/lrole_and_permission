package uz.pdp.lesson6_7role_and_permission.aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)  //we want to work it as method
@Retention(RetentionPolicy.RUNTIME)   //we say when it works (in runtime)
public @interface HuquqniTekshirish {  //it is annotation class for annotation

    String huquq();

}
