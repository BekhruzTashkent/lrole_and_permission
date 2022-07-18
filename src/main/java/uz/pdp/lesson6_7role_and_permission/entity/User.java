package uz.pdp.lesson6_7role_and_permission.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.lesson6_7role_and_permission.entity.enums.Huquq;
import uz.pdp.lesson6_7role_and_permission.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")

public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Lavozim lavozim;

    private boolean enabled;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    public User(String fullName, String username, String encode) {
    }


    //ALL IMPLEMENTED FROM USER DETAILS

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {   //it takes huquq list of lavozim
        List<Huquq> huquqList = this.lavozim.getHuquqList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
       for (Huquq huquq : huquqList) {                                             //huquq.for
           grantedAuthorities.add(new SimpleGrantedAuthority(huquq.name()));   //SIMILAR AS LINE DOWNWARD
       }

//        for (Huquq huquq : huquqList) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return huquq.name();
//                }
//            });
//        }
        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, Lavozim lavozim, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.lavozim = this.lavozim;
        this.enabled = enabled;
    }
}
