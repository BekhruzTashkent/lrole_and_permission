package uz.pdp.lesson6_7role_and_permission.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.lesson6_7role_and_permission.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data

public abstract class AbstractEntity {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;  //to know when account was created

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;  //to know when account was updated

    @JoinColumn(updatable = false)  //nobody can update
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY) //LAZY it brings from db only when we call it
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY) //LAZY it brings from db only when we call it
    private User updatedBy;

}
