package uz.pdp.lesson6_7role_and_permission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson6_7role_and_permission.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);  //find by username
    Optional<User> findByUsername(String username);
}
