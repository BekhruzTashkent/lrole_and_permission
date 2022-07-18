package uz.pdp.lesson6_7role_and_permission.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.lesson6_7role_and_permission.entity.User;
import uz.pdp.lesson6_7role_and_permission.exceptions.ForbiddenExceptions;

@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(huquqniTekshirish)")   //it says to which annotation work and when

    public void chechUserPermissionMyMethod(HuquqniTekshirish huquqniTekshirish){  //check its huquq
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //it is which know enters
        boolean exist = false;
        for(GrantedAuthority authority : user.getAuthorities()){   //it checks is this user have this authority
            if(authority.getAuthority().equals(huquqniTekshirish.huquq())){
                exist = true;
                break;
            }
        }
        if(!exist)
            throw new ForbiddenExceptions(huquqniTekshirish.huquq(), "Ruhsat yoq");  //that we create by own
    }
}
