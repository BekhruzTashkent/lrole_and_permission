package uz.pdp.lesson6_7role_and_permission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.lesson6_7role_and_permission.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

//this class we need for posting text like in news channel
public class Comment extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "text") //it means that it can be saved as text
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
